(ns o2w.messages.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
    [re-frame.core :refer (register-sub)]))

(register-sub
  :messages
  (fn [db _]
    (reaction (:messages @db))))
