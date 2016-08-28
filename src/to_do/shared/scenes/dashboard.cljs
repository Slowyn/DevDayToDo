(ns to-do.shared.scenes.dashboard
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe]]
            [to-do.shared.ui :as ui]))

(defn dashboard
  [{:keys [route nav]}]
  (let [todos (subscribe [:get-todos])]
    (fn [{:keys [route nav]}]
      [ui/view {:style {:flex 1}}
       (for [todo @todos]
         ^{:key (:id todo)}
         [ui/touchable-opacity {:style {:borderBottomWidth 1 :border-bottom-color "black"}}
          [ui/text (:title todo)]
          [ui/text (:desc todo)]])])))
