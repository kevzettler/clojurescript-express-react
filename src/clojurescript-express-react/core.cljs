
(ns clojurescript-express-react.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as string]
            [clojurescript-express-react.routes :as routes]))

;; It's common in Clojure-land to use (print my-string (pr-str
;; my-obj)) ClojureScript comes with a few utility functions to direct
;; output to either the terminal or to the browser console, depending
;; on where the compiled JS code is running.

;; Node:
(nodejs/enable-util-print!)
;; Browser:
;;(enable-console-print!)

;; defonce will only be called once for the lifetime of the app
;; (e.g. it's ignored on hot-reloads)
(defonce express
  (nodejs/require "express"))

(defonce serve-static
  (nodejs/require "serve-static"))

(defonce http
  (nodejs/require "http"))

(defonce server-port
  3000)

;; def and defn are called (and their contents redefined) on every
;; hot-reload
(def app
  (express))

;; Attach all of our routes (note the ! in the name hints that our app
;; reference will be mutated)
(routes/attach-routes! app))

;; Not sure why this is (def -main (fn [] ...)) instead of (defn -main
;; [] ...). The second is more idiomatic, but maybe it doesn't capture
;; the reference properly for *main-cli-fn*
(def -main
  (fn []
    (let [server (.createServer http (fn [req res]
                                       (app req res)))]
      (.listen server))))
    (println (string/join " " ["Server running on" server-port]) )

;; *main-cli-fn* is a semi-magic var that's used to set the entry
;; *point for a node app
(set! *main-cli-fn* -main)

(fw/start {})
