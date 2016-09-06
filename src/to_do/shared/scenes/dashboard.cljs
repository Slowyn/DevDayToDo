(ns to-do.shared.scenes.dashboard
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe]]
            [to-do.shared.routing.utils :as r-utils]
            [to-do.shared.routing.list :as r-list]
            [to-do.shared.ui :as ui]
            [to-do.global.styles :as g-style]
            [to-do.shared.components.list-todo :refer [list-todo]]
            [to-do.shared.components.empty-screen :refer [empty-screen]]))

(defn- render-row
  [data nav]
  (let [todo (js->clj data :keywordize-keys true)]
    (r/as-component [list-todo {:title (:title todo)
                                :done? (:done? todo)
                                :on-press #(r-utils/push
                                            (assoc r-list/todo
                                              :todo-id (:id todo)
                                              :title (:title todo))
                                            nav)}])))

(defn dashboard
  [{:keys [route nav]}]
  (let [todos (subscribe [:get-todos])
        data-source (ui/create-ds {:rowHasChanged (fn [r1 r2] (not= r1 r2))})]
    (fn [{:keys [route nav]}]
      [ui/view {:style g-style/container}
       (if (-> @todos count pos?)
         [ui/list-view {:dataSource (.cloneWithRows data-source (clj->js @todos))
                        :enableEmptySections true
                        :renderRow #(render-row % nav)}]
         [ui/scroll
          [empty-screen {:title "You have notes any todos yet!"
                         :btn-label "Don't you wish to create one?"
                         :on-press #(r-utils/push r-list/add-todo nav)}]])])))
