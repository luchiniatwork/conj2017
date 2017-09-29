(ns e-commerce-demo.resolvers.categories
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [e-commerce-demo.query :as query]))

(defn resolver [db]
  (fn [context arguments value]
    (let [q `[{:categories/categories ~(query/selectors context)}]
          pred (if (nil? arguments) identity #(= (:db/id %) (:byId arguments)))
          {:keys [categories/categories]} (query/query db q {:categories/categories pred})]
      (transform-keys ->camelCaseKeyword categories))))
