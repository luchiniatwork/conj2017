(ns e-commerce-demo.resolvers.products
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [om.next :as om]))

(defn resolver [db]
  (fn [context arguments value]
    (let [{:keys [products/products]} (om/db->tree '[{:products/products [*]}] db db)]
      (transform-keys ->camelCaseKeyword products))))
