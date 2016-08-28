(ns to-do.events
  (:require
    [re-frame.core :refer [reg-event after]]
    [schema.core :as s :include-macros true]
    [to-do.db :as db :refer [app-db schema]]))

;; -- Middleware ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/wiki/Using-Handler-Middleware
;;

(defn check-and-throw
  "throw an exception if db doesn't match the schema."
  [a-schema db]
  (if-let [problems (s/check a-schema db)]
    (throw (js/Error. (str "schema check failed: " problems)))))

(def validate-spec-mw
  (if goog.DEBUG
    (after (partial check-and-throw schema))
    []))

;; -- Handlers --------------------------------------------------------------

(reg-event
  :initialize-db
  validate-spec-mw
  (fn [_ _]
    app-db))
