(ns e-commerce-demo.resolvers.products
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [e-commerce-demo.query :as query]))

(defn resolver [db]
  (fn [context arguments value]
    (let [q `[{:products/products ~(query/selectors context)}]
          pred (if (nil? arguments) identity #(= (:db/id %) (:byId arguments)))
          {:keys [products/products]} (query/query db q {:products/products pred})]
      (transform-keys ->camelCaseKeyword products))))
