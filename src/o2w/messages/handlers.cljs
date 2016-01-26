(ns o2w.messages.handlers
  (:require
    [o2w.messages.utils :refer [UUIDv4]]
    [re-frame.core :as re-frame :refer [register-handler dispatch dispatch-sync]]))

(register-handler
  :message
  (fn [db [_ message]]
    (-> db
      (assoc-in [:messages]
        (conj (db :messages) (-> message
                               (assoc :timeout (message :timeout 3000))
                               (assoc :id (message :id
                                            (str (.-uuid (UUIDv4)))))))))))
(register-handler
  :message-expire
  (fn [db [_ id]]
    (-> db
      (assoc-in [:messages] (remove #(= id (% :id)) (get-in db [:messages]))))))
