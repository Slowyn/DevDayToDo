(ns to-do.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-sub-raw]]))


(reg-sub-raw
  :get-todos
  (fn [db [_ _]]
    (reaction (:todos @db))))

(reg-sub-raw
  :get-todo-info
  (fn [db [_ todo-id]]
    (reaction
      (some #(when (= todo-id (:id %)) %) (:todos @db)))))