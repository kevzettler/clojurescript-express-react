(ns hello-world.dev
  (:require [figwheel.client]
            [hello-world.core]))

(defn -main []
  (figwheel.client/start)
  (hello-world.core/-main))

(set! *main-cli-fn* -main)
