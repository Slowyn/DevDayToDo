(ns to-do.db
  (:require [schema.core :as s :include-macros true]))

;; schema of app-db
(def schema {:todos [{:id s/Any
                      :title s/Str
                      :desc s/Str
                      :done? s/Bool}]})

;; initial state of app-db
(def app-db {:todos []})
