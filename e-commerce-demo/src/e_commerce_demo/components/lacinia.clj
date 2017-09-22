(ns e-commerce-demo.components.lacinia
  (:require [com.stuartsierra.component :as component]
            [com.walmartlabs.lacinia.pedestal :as lacinia]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [ring.util.response :as response]))

(defrecord Lacinia [schema routes]
  component/Lifecycle
  (start [this]
    (println "Lacinia: starting...")
    {::http/join? false
     ::http/type :jetty
     ::http/routes (concat
                    (route/expand-routes
                     (lacinia/graphql-routes schema {:graphiql false}))
                    (route/expand-routes routes)
                    (route/expand-routes #{["/graphiql"
                                            :get (fn [request]
                                                   (response/redirect "/graphiql/index.html"))
                                            :route-name ::graphiql-ide-index]}))})
  (stop [this]
    (println "Lacinia: stopping...")))

(defn new-lacinia []
  (map->Lacinia {}))
