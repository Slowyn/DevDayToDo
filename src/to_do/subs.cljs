(ns to-do.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-sub-raw]]))


(reg-sub-raw
  :get-todos
  (fn [db [_ _]]
    (reaction (:todos @db))))