(ns magitek-clicker.events
  (:require [re-frame.core :as re-frame]
            [magitek-clicker.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :set-active-panel
 (fn [{:keys [db]} [_ active-panel]]
   (cond-> {:db (assoc db :active-panel active-panel)}
     (= active-panel :game-panel)
     (assoc :dispatch [::tick]))))

(re-frame/reg-event-fx
 ::tick
 (fn [{:keys [db]} _]
   {:db (update db :tick inc)
    :dispatch-later [{:ms 1000 :dispatch [::tick]}]}))
