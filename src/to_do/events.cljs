(ns to-do.events
  (:require
    [re-frame.core :refer [reg-event after]]
    [schema.core :as s :include-macros true]
    [to-do.utils.helpers :refer [find-index]]
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

(reg-event
  :add-todo
  validate-spec-mw
  (fn [db [_ todo-data]]
    (update db :todos #(conj % (assoc todo-data
                                 :id (random-uuid)
                                 :done? false)))))

(reg-event
  :edit-todo
  validate-spec-mw
  (fn [db [_ todo-id todo-data]]
    (let [todo-index (find-index #(not= (:id %) todo-id) (:todos db))]
      (assoc-in db [:todos todo-index] (merge (get-in db [:todos todo-index]) todo-data)))))
