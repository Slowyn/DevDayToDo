(ns to-do.shared.components.empty-screen
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]
            [to-do.shared.components.button :refer [button]]))

(defn empty-screen
  [{:keys [title btn-label on-press]
    :or {title ""
         btn-label ""
         on-press #()}}]
  (let [opacity (r/atom (ui/animated-value. 0))
        translate-y (r/atom (ui/animated-value. 150))]
    (r/create-class
      {:display-name "empty-screen"
       :component-did-mount
       (fn []
         (.start (.parallel ui/animated #js[(.timing ui/animated @opacity #js{:toValue 1
                                                                              :duration 700})
                                            (.spring ui/animated @translate-y #js{:toValue 0
                                                                                  :duration 1000})])))
       :reagent-render
       (fn [{:keys [title btn-label on-press]
             :or {title ""
                  btn-label ""
                  on-press #()}}]
         [ui/animated-view {:style {:padding-vertical 10
                                    :opacity @opacity
                                    :transform [{:translateY @translate-y}]}}
          [ui/image {:source (js/require "./images/cljs.png")
                     :style {:align-self "center"}}]
          [ui/view {:height 30}]
          [ui/text {:style {:align-self "center"}} title]
          [ui/view {:height 30}]
          [button {:label btn-label
                   :on-press on-press}]])})))
