(ns to-do.shared.routing.config
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]
            [to-do.shared.routing.methods :refer [push pop]]))

(defn scene-configurator
  "Simple scene configurator"
  [js-route]
  (let [route (js->clj js-route :keywordize-keys true)]
    (if (nil? (:sceneConfig route))
      (.-PushFromRight ui/scene-configs)
      (:sceneConfig route))))

(defn route-mapper
  "description of routes for navigator"
  [route nav]
  (r/as-component
    [ui/view {:style {:flex 1
                      :padding-top 100}}
     [ui/text (aget route "name")]
     [ui/touchable-opacity {:on-press #(push {:name (str "scene " (inc (aget route "index")))
                                              :index (inc (aget route "index"))}
                                             nav)
                            :style {:background-color "orange"
                                    :padding 40
                                    :align-items "center"
                                    :justify-content "center"}}
      [ui/text "Next page"]]
     (if (not= (aget route "name") "home")
       [ui/touchable-opacity {:on-press #(pop nav)
                              :style {:margin-top 20
                                      :background-color "#FF0"
                                      :padding 40
                                      :align-items "center"
                                      :justify-content "center"}}
        [ui/text "back"]])]))

(def nav-bar-route-mapper
  "mapper for navbar"
  {:LeftButton (fn [route nav index nav-state]
                 (when-not (-> index zero?)
                   (r/as-component [ui/touchable-opacity {:on-press #(pop nav)}
                                    [ui/view {:align-items "center"
                                              :padding-horizontal 8
                                              :justify-content "center"
                                              :height 44}
                                     [ui/text "Back"]]])))
   :Title (fn [route nav index nav-state]
            (r/as-component [ui/view {:align-items "center"
                                      :justify-content "center"
                                      :height 44}
                             [ui/text (aget route "title")]]))
   :RightButton #()})
