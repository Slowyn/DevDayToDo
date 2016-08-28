(ns to-do.shared.components.button
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]))
(def style {:container {:height 40
                        :justify-content "center"
                        :align-items "center"
                        :border-radius 5
                        :margin 10
                        :background-color "#333"}
            :label {:color "#fff"}})

(defn button
  ""
  [{:keys [on-press label]
    :or {on-press #()
         label "default label"}}]
  [ui/touchable-opacity {:on-press on-press
                         :style (:container style)}
   [ui/text {:style (:label style)} label]])
