(ns e-commerce-demo.components.pedestal
    (:require [com.stuartsierra.component :as component]
              [io.pedestal.http :as http]))

(defn ^:private test?
  [service-map]
  (= :test (:env service-map)))

(defrecord PedestalServiceMap [app-config base-service-map]
  component/Lifecycle
  (start [this]
    (let [{:keys [env http-port]
           :or {env :dev http-port 3000}} app-config]
      (println "Pedestal: will bind to port" http-port)
      (assoc (or base-service-map {})
             :env env
             ::http/port http-port
             ::http/allowed-origins {:creds true :allowed-origins (constantly true)}))))

(defn new-pedestal-service-map
  ([] (new-pedestal-service-map {}))
  ([m] (map->PedestalServiceMap m)))

(defrecord Pedestal [service-map service]
  component/Lifecycle
  (start [this]
    (println "Pedestal: starting...")
    (if service
      this
      (cond-> service-map
        true http/create-server
        (not (test? service-map)) http/start
        true ((partial assoc this :service)))))
  (stop [this]
    (println "Pedestal: stopping...")
    (when (and service (not (test? service-map)))
      (http/stop service))
    (assoc this :service nil)))

(defn new-pedestal
  ([] (new-pedestal {}))
  ([m] (map->Pedestal m)))
