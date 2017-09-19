(ns e-commerce-demo.components.lacinia
  (:require [com.stuartsierra.component :as component]
            [com.walmartlabs.lacinia.pedestal :as lacinia]
            [io.pedestal.http.route :as route]))

(defrecord Lacinia [schema routes]
  component/Lifecycle
  (start [this]
    (println "Lacinia: starting...")
    (lacinia/pedestal-service schema
                              {:graphiql true
                               :routes (concat
                                        (route/expand-routes
                                         (lacinia/graphql-routes schema {:graphiql true}))
                                        (route/expand-routes
                                         routes))}))
  (stop [this]
    (println "Lacinia: stopping...")))

(defn new-lacinia []
  (map->Lacinia {}))
