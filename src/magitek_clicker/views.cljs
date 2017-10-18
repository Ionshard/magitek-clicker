(ns magitek-clicker.views
  (:require [re-frame.core :as re-frame]
            [magitek-clicker.events :as events]
            [soda-ash.core :as sa]))


;; home

(defn home-panel []
  [sa/GridColumn
   [sa/Container "Welcome to Magitek Clicker Game"]])


;; game

(defn game-panel []
  [sa/GridColumn
   [sa/Container "Game ON!"]])


;; main

(defn- menu-item [text link key]
  (let [active-panel @(re-frame/subscribe [:active-panel])]
    [sa/MenuItem {:href link :name text :active (= active-panel key)}]))

(defn- menu []
  [sa/GridColumn
   [sa/Menu {:pointing true
             :secondary true}
    [sa/MenuItem {:header true} [sa/Icon {:name :lightning}] "Magitek Clicker"]
    [menu-item "Home" "#/" :home-panel]
    [menu-item "Play" "#/game" :game-panel]]])

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :game-panel [game-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel @(re-frame/subscribe [:active-panel])]
    [sa/Grid
     [sa/GridRow {:id "menu"}
      [menu]]
     [sa/GridRow {:id "content"}
      [show-panel active-panel]]]))
