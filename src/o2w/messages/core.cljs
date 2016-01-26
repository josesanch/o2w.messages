(ns o2w.messages.core
  (:require
    [cljs.core.async :refer [<! timeout]]
    [o2w.messages.handlers]
    [o2w.messages.subs]
    [re-frame.core :refer [subscribe dispatch]])
  (:require-macros [cljs.core.async.macros :as m :refer [go]]))

(defn- close-message [item]
  (dispatch [:message-expire (item :id)]))

(defn- set-message-timeout [item]
  (go
    (<! (timeout (:timeout item)))
    (close-message item)))

(defn message [item]
  (set-message-timeout item)
  [:div.alert.alert-dismissible.fade.in {:class (str "alert-" (item :class "info"))
                                         :role  "alert"}
   [:button {:type     :button :class "close"
             :on-click #(close-message item)} [:span {:aria-hidden :true} "x"]]
   [:p (item :text)]
   ])


(defn messages []
  (let [message-list (subscribe [:messages])]
    (fn []
      [:div.messages
       (for [item @message-list]
         ^{:key (item :id)}
         [message item])])))
