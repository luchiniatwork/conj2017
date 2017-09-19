(ns e-commerce-demo.components.db
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [com.stuartsierra.component :as component]))

(defrecord Database [db]
  component/Lifecycle
  (start [this]
    (println "Database: starting...")
    (assoc this :db (-> "db.edn"
                        io/resource
                        slurp
                        edn/read-string)))
  (stop [this]
    (println "Database: stopping...")
    (assoc this :db nil)))

(defn new-database []
  (map->Database {}))
