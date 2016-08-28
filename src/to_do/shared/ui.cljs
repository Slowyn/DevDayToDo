(ns to-do.shared.ui
  (:require [reagent.core :as r]
            [to-do.shared.rn-api :refer [react-native]]))

(def view (r/adapt-react-class (.-View react-native)))
(def text (r/adapt-react-class (.-Text react-native)))
(def image (r/adapt-react-class (.-Image react-native)))
(def touchable-opacity (r/adapt-react-class (.-TouchableOpacity react-native)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight react-native)))
(def text-input (r/adapt-react-class (.-TextInput react-native)))
(def navigator-component (.-Navigator react-native))
(def navigator (r/adapt-react-class navigator-component))
(def navigation-bar (r/adapt-react-class (.-NavigationBar navigator-component)))
(def scene-configs (-> react-native .-Navigator.SceneConfigs))

