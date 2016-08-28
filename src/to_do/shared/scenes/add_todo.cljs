(ns to-do.shared.scenes.add-todo
  (:require [reagent.core :as r]
            [to-do.shared.ui :as ui]
            [re-frame.core :refer [dispatch]]
            [to-do.shared.routing.utils :as r-utils]
            [to-do.shared.components.input-box :refer [input-box]]
            [to-do.shared.components.button :refer [button]]
            [clojure.string :refer [blank?]]
            [to-do.shared.rn-api :refer [alert]]
            [to-do.shared.routing.utils :as utils]))


(def style {:container {:flex 1}})

(defn add-todo
  [{:keys [nav route]}]
  (let [title-atom (r/atom "")
        desc-atom (r/atom "")]
    (fn [{:keys [nav route]}]
      [ui/view {:style (:container style)}
       [input-box {:label "Title"
                   :placeholder "Title"
                   :onChangeText #(reset! title-atom %)}]
       [input-box {:label "Description"
                   :placeholder "Description"
                   :onChangeText #(reset! desc-atom %)
                   :multiline true}]
       [button {:on-press (fn []
                            (if (and (blank? @title-atom) (blank? @desc-atom))
                              (alert "Not enough data" "You have forgotten to type title or description")
                              (do
                                (dispatch [:add-todo {:title @title-atom
                                                      :desc @desc-atom}])
                                (utils/pop nav))))
                :label "+ ToDo"}]])))
