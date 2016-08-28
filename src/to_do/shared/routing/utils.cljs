(ns to-do.shared.routing.utils
  (:require [to-do.shared.ui :refer [scene-configs]]))

(defn pop
  "usual pop"
  [nav]
  (.pop nav))

(defn push
  "Usual push"
  [new-route nav]
  (.push nav (clj->js new-route)))

(defn push-modal
  "Usual push but with modal animation"
  [new-route nav]
  (push (merge new-route {:sceneConfig (.-FloatFromBottomAndroid scene-configs)}) nav))
