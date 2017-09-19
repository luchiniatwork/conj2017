(ns e-commerce-demo.components.resolvers
  (:require [e-commerce-demo.resolvers.core :as resolvers]
            [com.stuartsierra.component :as component]))

(defrecord Resolvers [db resolvers]
  component/Lifecycle
  (start [this]
    (println "Resolvers: starting...")
    (assoc this :resolvers (resolvers/build-resolvers (:db db))))
  (stop [this]
    (println "Resolvers: stopping...")
    (assoc this :db nil)))

(defn new-resolvers []
  (map->Resolvers {}))
