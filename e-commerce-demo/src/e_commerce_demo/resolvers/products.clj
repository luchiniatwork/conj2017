(ns e-commerce-demo.resolvers.products
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [e-commerce-demo.query :as query]))

(defn resolver [db]
  (fn [context arguments value]
    (let [q `[{:products/products ~(query/selectors context)}]
          {:keys [products/products]} (query/query db q)]
      (transform-keys ->camelCaseKeyword products))))
