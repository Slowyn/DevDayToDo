(ns to-do.shared.scenes.todo
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]))

(defn todo
  []
  (let []
    [ui/view {:style {:flex 1}}
     [ui/text {:margin-top 200} "add-todo"]]))
