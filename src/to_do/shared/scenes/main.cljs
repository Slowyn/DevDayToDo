(ns to-do.shared.scenes.main
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [to-do.shared.ui :as ui]
            [to-do.shared.routing.config :as r-config]
            [to-do.shared.routing.list :as list]))

(defn app []
  [ui/navigator {:configureScene r-config/scene-configurator
                 :renderScene r-config/route-mapper
                 :style {:padding-top 64}
                 :initialRoute list/dashboard
                 :navigationBar (r/as-component
                                  [ui/navigation-bar {:routeMapper r-config/nav-bar-route-mapper
                                                      :style {:background-color "rgba(230, 230, 230, 1)"
                                                              :borderBottomWidth 0.5
                                                              :border-bottom-color "rgba(10,10,10,.7)"}}])}])
