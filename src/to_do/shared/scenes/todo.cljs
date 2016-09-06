(ns to-do.shared.scenes.todo
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [to-do.shared.ui :as ui]
            [to-do.global.styles :as g-style]))
(def style
  {:content {:padding 20
             :border-bottom-color "#ccc"
             :border-bottom-width 0.5}
   :title {:font-size 20
           :font-weight "bold"
           :color "#666"}
   :desc {:font-size 18
          :font-weight "300"
          :color "#333"
          :letter-spacing 0}
   :row {:flex-direction "row"}})

(defn todo
  [{:keys [route nav]}]
  (let [todo-info (subscribe [:get-todo-info (-> route :todo-id)])
        start-done? (reaction (:done? @todo-info))
        done? (r/atom @start-done?)
        bgc (r/atom (ui/animated-value. @start-done?))
        start-anim (fn [x]
                     (.start (.timing ui/animated @bgc #js {:toValue x
                                                            :duration 400})))]
    (fn [{:keys [route nav]}]
      [ui/view {:style g-style/container}
       [ui/animated-view {:style (assoc
                                   g-style/container
                                   :background-color (.interpolate @bgc (clj->js {:inputRange [0 1]
                                                                                  :outputRange ["rgba(255,255,255,1)"
                                                                                                "rgba(20,200,20,.2)"]})))}
        [ui/view {:style (:content style)}
         [ui/text {:style (:title style)} "Description"]
         [ui/scroll {:height 120}
          [ui/text {:style (:desc style)} (:desc @todo-info)]]]
        [ui/view {:style (merge (:content style) (:row style))}
         [ui/view {:style {:flex 10 :justify-content "center"}}
          [ui/text {:style (:title style)} "Have you done it?"]]
         [ui/view {:style {:flex 1 :align-items "flex-end"}}]
         [ui/switch {:value @done?
                     :onChange (fn []
                                 (swap! done? false?)
                                 (start-anim (if @done? 1 0))
                                 (dispatch [:edit-todo
                                            (:id @todo-info)
                                            {:done? @done?}]))}]]]])))
