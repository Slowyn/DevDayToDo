(ns to-do.shared.routing.config
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]
            [to-do.shared.routing.utils :as r-utils]
            [to-do.shared.routing.list :as list]
            [to-do.shared.scenes.dashboard :refer [dashboard]]
            [to-do.shared.scenes.add-todo :refer [add-todo]]
            [to-do.shared.scenes.todo :refer [todo]]))

(defn scene-configurator
  "Simple scene configurator"
  [js-route]
  (let [route (js->clj js-route :keywordize-keys true)]
    (if (nil? (:sceneConfig route))
      (.-PushFromRight ui/scene-configs)
      (:sceneConfig route))))

(defn check-name
  "Compare name with name of standart"
  [route-name standart]
  (when-not (nil? (:name standart))
    (= route-name (:name standart))))

(defn route-mapper
  "description of routes for navigator"
  [route nav]
  (let [route-name (aget route "name")
        scene (cond
                (check-name route-name list/dashboard) [dashboard {:nav nav :route (js->clj route :keywordize-keys true)}]
                (check-name route-name list/add-todo) [add-todo {:nav nav :route (js->clj route :keywordize-keys true)}]
                (check-name route-name list/todo) [todo {:nav nav :route (js->clj route :keywordize-keys true)}]
                :else [dashboard {:nav nav :route (js->clj route :keywordize-keys true)}])]
    (r/as-component scene)))

(def nav-bar-route-mapper
  "mapper for navbar"
  {:LeftButton (fn [route nav index nav-state]
                 (when-not (-> index zero?)
                   (r/as-component [ui/touchable-opacity {:on-press #(r-utils/pop nav)}
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
   :RightButton (fn [route nav index nav-state]
                  (let [route-name (aget route "name")
                        btn (cond
                              (check-name route-name list/dashboard) [ui/touchable-opacity {:on-press #(r-utils/push list/add-todo nav)}
                                                                      [ui/view {:align-items "center"
                                                                                :padding-horizontal 8
                                                                                :justify-content "center"
                                                                                :height 44}
                                                                       [ui/text "Add ToDo"]]])]
                    (r/as-component btn)))})
