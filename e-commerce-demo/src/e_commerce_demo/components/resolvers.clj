(ns e-commerce-demo.components.resolvers
  (:require [e-commerce-demo.resolvers.core :as resolvers]
            [com.stuartsierra.component :as component]))

(defrecord Resolvers [db delayer-prods delayer-cats resolvers]
  component/Lifecycle
  (start [this]
    (println "Resolvers: starting...")
    (assoc this :resolvers (resolvers/build-resolvers this)))
  (stop [this]
    (println "Resolvers: stopping...")
    (assoc this :resolvers nil)))

(defn new-resolvers []
  (map->Resolvers {}))
