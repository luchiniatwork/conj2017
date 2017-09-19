(ns e-commerce-demo.components.schema
    (:require [clojure.java.io :as io]
              [com.stuartsierra.component :as component]
              [com.walmartlabs.lacinia.schema :as schema]
              [com.walmartlabs.lacinia.util :refer [attach-resolvers]]
              [umlaut.generators.lacinia :as umlaut]))

(defrecord Schema [umlaut-file resolvers compiled-schema]
  component/Lifecycle
  (start [this]
    (println "Schema: starting schema...")
    (-> umlaut-file
        io/resource
        umlaut/gen
        (attach-resolvers (:resolvers resolvers))
        schema/compile))
  (stop [this]
    (println "Schema: stopping schema...")))

(defn new-schema []
  (map->Schema {}))
