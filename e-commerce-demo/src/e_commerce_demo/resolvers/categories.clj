(ns e-commerce-demo.resolvers.categories
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [e-commerce-demo.query :as query]))

(defn resolver [db]
  (fn [context arguments value]
    (let [q `[{:categories/categories ~(query/selectors context)}]
          {:keys [categories/categories]} (query/query db q)]
      (transform-keys ->camelCaseKeyword categories))))
