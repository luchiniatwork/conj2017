(ns e-commerce-demo.resolvers.products
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [e-commerce-demo.query :as query]))

(defn resolver [db delayer]
  (fn [context arguments value]
    (let [q `[{:products/products ~(query/selectors context)}]
          pred (if (nil? arguments) identity #(= (:db/id %) (:byId arguments)))
          {:keys [products/products]} (query/query db q {:products/products pred} delayer)]
      (transform-keys ->camelCaseKeyword products))))
