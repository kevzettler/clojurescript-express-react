(ns clojurescript-express-react.routes
  (:require [om.core :as om]
            [om.dom :as dom]))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/h1 nil (:text data)))))

(defn rendertostring []
    (dom/render-to-str (om/build widget {:text "React widget text prop"})))

(defn response-thing []
    (om/root widget {:text "React Widget text prop"}
        {:target (. js/document (getElementById "my-app"))}))

(defn attach-routes [app]
    (. app (get "/hello"
      (fn [req res] (. res (send "Hello World")))))
    
    (. app (get "/test"
      (fn [req res] (. res (send "This is a test"))))))