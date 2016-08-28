(ns to-do.shared.rn-api)

(def react-native (js/require "react-native"))
(def app-registry (.-AppRegistry react-native))

(defn alert [title desc]
  (.alert (.-Alert react-native) title desc))


