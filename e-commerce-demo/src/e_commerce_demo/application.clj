(ns e-commerce-demo.application
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [e-commerce-demo.components.db :as db]
            [e-commerce-demo.components.lacinia :as lacinia]
            [e-commerce-demo.components.pedestal :as pedestal]
            [e-commerce-demo.components.resolvers :as resolvers]
            [e-commerce-demo.components.routes :as routes]
            [e-commerce-demo.components.schema :as schema]
            [e-commerce-demo.components.delayer :as delayer]
            [e-commerce-demo.config :as config]))

(defn app-system [app-config]
  (component/system-map
   ;; Basic app configurations
   :app-config app-config
   :umlaut-file "schemas/e-commerce.umlaut"

   :db (db/new-database)

   :delayer-prods-config {:median 100
                          :std-dev 100}
   
   :delayer-prods (component/using (delayer/new-delayer)
                                   {:config :delayer-prods-config})

   :delayer-cats-config {:median 2000
                         :std-dev 2000}
   
   :delayer-cats (component/using (delayer/new-delayer)
                                  {:config :delayer-cats-config})
   
   ;; Routes to be added to the basic GraphQL routes
   :routes routes/routes

   ;; Map of GraphQL resolvers to be added to the schema
   :resolvers (component/using (resolvers/new-resolvers)
                               [:db :delayer-prods :delayer-cats])

   ;; GraphQL schema and Lacinia itself
   :schema (component/using (schema/new-schema)
                            [:resolvers :umlaut-file])
   :lacinia (component/using (lacinia/new-lacinia)
                             [:schema :routes])

   ;; Pedestal configuration and initialization
   :service-map (component/using (pedestal/new-pedestal-service-map)
                                 {:app-config :app-config
                                  :base-service-map :lacinia})
   :pedestal (component/using (pedestal/new-pedestal)
                              [:service-map])))

(defn -main [& _]
  (-> (config/config)
      app-system
      component/start))
