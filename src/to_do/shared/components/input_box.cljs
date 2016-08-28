(ns to-do.shared.components.input-box
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]))


;;
;                40
;--------------------------------------
;|<- 10 ->|<--------- 30 ------------>|
;10/30 = 1/3
;flex: 1
;flex: 3
;;


(def styles {:container {:height 38
                         :flex-direction "row"
                         :borderBottomWidth 0.5
                         :border-color "#cccccc"}
             :high-container {:height 70
                              :flex-direction "row"
                              :borderBottomWidth 0.5
                              :border-color "#cccccc"}
             :label-container {:flex 1
                               :padding-left 15
                               :justify-content "center"}
             :label {:color "#959595"
                     :font-size 11
                     :font-weight "700"}
             :input-container {:flex 3}
             :input {:flex 1
                     :color "#262626"
                     :font-size 12}})

(defn input-box
  [props]
  [ui/view {:style (if (:multiline props) (:high-container styles) (:container styles))}
   [ui/view {:style (:label-container styles)}
    [ui/text {:style (:label styles) :numberOfLines 1} (:label props)]]
   [ui/view {:style (:input-container styles)}
    [ui/text-input {:style (:input styles)
                    :ref "input"
                    :multiline (:multiline props)
                    :defaultValue (:defaultValue props)
                    :placeholder (:label props)
                    :selectionColor (or (:selectionColor props) "#3e9e33")
                    :keyboardAppearance (or (:keyboardAppearance props) "dark")
                    :onChangeText (or (:onChangeText props) #())
                    :keyboardType (or (:keyboardType props) "default")}]]])