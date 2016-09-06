(ns to-do.ios.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [to-do.events]
            [to-do.subs]
            [to-do.shared.ui :as ui]
            [to-do.shared.rn-api :refer [app-registry alert]]
            [to-do.shared.scenes.main :refer [app]]))

(defn app-root []
  [app])

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "ToDo" #(r/reactify-component app-root)))
