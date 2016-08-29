(ns to-do.shared.scenes.dashboard
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe]]
            [to-do.shared.ui :as ui]
            [to-do.global.styles :as g-style]))

(defn dashboard
  [{:keys [route nav]}]
  (let [todos (subscribe [:get-todos])
        data-source (ui/create-ds {:rowHasChanged (fn [r1 r2] (not= r1 r2))})
        render-row (fn [data]
                     (let [todo (js->clj data :keywordize-keys true)]
                       (r/as-component [ui/touchable-opacity {:style {:borderBottomWidth 1 :border-bottom-color "black"}}
                                        [ui/text (:title todo)]
                                        [ui/text (:desc todo)]])))]
    (fn [{:keys [route nav]}]
      [ui/view {:style g-style/container}
       [ui/list-view {:dataSource (.cloneWithRows data-source (clj->js @todos))
                      :enableEmptySections true
                      :renderRow render-row}]])))
