(ns magitek-clicker.ticker
  (:require [re-frame.core :as re-frame]))

(def db-key ::db)

(defn running? [db]
  (get-in db [db-key :running] false))

(re-frame/reg-event-fx
 ::tick
 (fn [{:keys [db]} _]
   (when (running? db)
     {:db (update-in db [db-key :tick] inc)
      :dispatch-later [{:ms 1000 :dispatch [::tick]}]})))

(re-frame/reg-event-fx
 ::start
 (fn [{:keys [db]} _]
   (when-not (running? db)
     {:db (assoc-in db [db-key :running] true)
      :dispatch [::tick]})))

(re-frame/reg-event-fx
 ::stop
 (fn [{:keys [db]} _]
   (when (running? db)
     {:db (assoc-in db [db-key :running] false)})))
