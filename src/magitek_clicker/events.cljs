(ns magitek-clicker.events
  (:require [re-frame.core :as re-frame]
            [magitek-clicker.db :as db]
            [magitek-clicker.ticker :as ticker]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :set-active-panel
 (fn [{:keys [db]} [_ active-panel]]
   (cond-> {:db (assoc db :active-panel active-panel)}
     (= active-panel :game-panel)
     (assoc :dispatch [:initialize-game])
     (not= active-panel :game-panel)
     (assoc :dispatch [::ticker/stop]))))

(re-frame/reg-event-fx
 :initialize-game
 (fn [_ _]
   {:dispatch-n [[::ticker/start]]}))
