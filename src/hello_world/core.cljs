(ns hello-world.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as string]))

(nodejs/enable-util-print!)

(defonce express (nodejs/require "express"))
(defonce http (nodejs/require "http"))
(defonce server-port 3000)

;; app gets redefined on reload
(def app (express))

(. app (get "/hello"
      (fn [req res] (. res (send "Hello world")))))

(def -main
  (fn []
    (doto (.createServer http #(app %1 %2))
      (.listen server-port))))
    (println (string/join " " ["Server running on" server-port]) )
    

(set! *main-cli-fn* -main)