(ns e-commerce-demo.query
  (:require [camel-snake-kebab.core :refer [->kebab-case-string]]
            [com.walmartlabs.lacinia.executor :as executor]
            [om.next :as om]))

(defn ^:private real-key [k]
  (keyword (->kebab-case-string (namespace k))
           (->kebab-case-string (name k))))

(defn ^:private query-selectors [selections]
  (reduce-kv (fn [a k v]
               (let [payload (if (map? v)
                               {(real-key k) (query-selectors (:selections v))}
                               (real-key k))]
                 (conj a payload)))
             []
             selections))

(defn selectors [context]
  (let [tree (executor/selections-tree context)]
    (query-selectors tree)))

(defn query [db q]
  (om/db->tree q db db))
