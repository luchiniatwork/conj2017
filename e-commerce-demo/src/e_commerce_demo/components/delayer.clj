(ns e-commerce-demo.components.delayer
  (:require [com.stuartsierra.component :as component])
  (:import [java.util Random]))

(defn ^:private delayer-factory
  [mean std-dev]
  (let [rng (Random.)]
    #(+ mean
        (* std-dev
           (.nextGaussian rng)))))

(defrecord Delayer [config delayer]
  component/Lifecycle
  (start [this]
    (println "Delayer: starting...")
    (let [{:keys [mean std-dev]} config]
      (assoc this
             :delayer (delayer-factory mean std-dev))))
  (stop [this]
    (println "Delayer: stopping...")
    (assoc this :delayer nil)))

(defn new-delayer []
  (map->Delayer {}))
