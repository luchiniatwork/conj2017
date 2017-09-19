(ns e-commerce-demo.config
    (:require [environ.core :refer [env]]))

(defn config []
  {:http-port (Integer. (or (env :port) 3000))
   :env (or (env :env) :dev)})
