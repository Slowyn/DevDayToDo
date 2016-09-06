(ns to-do.shared.components.list-todo
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]))

(def style
  {:container {:height 44
               :padding-left 30
               :border-bottom-width 0.5
               :border-bottom-color "#ccc"
               :flex-direction "row"}
   :main-content {:flex 6
                  :justify-content "center"}
   :after {:flex 1
           :justify-content "center"
           :align-items "flex-end"
           :padding-right 15}
   :text {:color "#333"
          :font-size 18
          :font-weight "300"}})

(defn list-todo
  [{:keys [title done? on-press]
    :or {title ""
         done? false
         on-press #()}}]
  [ui/touchable-highlight {:on-press on-press
                           :underlayColor "#ccc"}
   [ui/view {:style (assoc
                      (:container style)
                      :background-color (if done? "rgba(20,200,20,.2)" "#fff"))}
    [ui/view {:style (:main-content style)}
     [ui/text {:style (:text style)} title]]
    [ui/view {:style (:after style)}
     [ui/image {:source (js/require "./images/icon-arrow.png")
                :resize-mode "contain"
                :style {:width 12 :height 12}}]]]])
