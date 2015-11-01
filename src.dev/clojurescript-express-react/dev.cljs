(ns clojurescript-express-react.dev
  (:require [figwheel.client]
            [clojurescript-express-react.core]))

(defn -main []
  (figwheel.client/start)
  (clojurescript-express-react.core/-main))

(set! *main-cli-fn* -main)
