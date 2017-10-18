(ns user
  (:require [figwheel-sidecar.repl-api :as repl]))

(defn- start []
  (repl/start-figwheel!))

(defn- cljs-repl []
  (repl/cljs-repl))

