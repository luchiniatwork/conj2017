(ns e-commerce-demo.query
  (:require [camel-snake-kebab.core :refer [->kebab-case-string]]
            [com.walmartlabs.lacinia.executor :as executor]
            [om.next :as om]))

(defn ^:private real-key [k]
  (keyword (->kebab-case-string (namespace k))
           (->kebab-case-string (name k))))

(defn ^:private query-selectors [selections]
  (conj
   (reduce-kv (fn [c k v]
                (let [payload (if (map? v)
                                {(real-key k) (query-selectors (:selections v))}
                                (real-key k))]
                  (conj c payload)))
              []
              selections)
   :db/id))

(defn selectors [context]
  (let [tree (executor/selections-tree context)]
    (query-selectors tree)))

(defn query
  ([db q delayer] 
   (Thread/sleep (max 0 (delayer)))
   (om/db->tree q db db))
  ([db q preds delayer]
   (Thread/sleep (max 0 (delayer)))
   (reduce-kv (fn [m k v]
                (let [pred (or (get preds k) identity)]
                  (assoc m k (filter pred v))))
              {}
              (om/db->tree q db db))))
