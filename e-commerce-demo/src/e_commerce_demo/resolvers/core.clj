(ns e-commerce-demo.resolvers.core
  (:require [e-commerce-demo.resolvers.categories :as categories]
            [e-commerce-demo.resolvers.products :as products]))

(defn build-resolvers [db]
  {:resolver-products (products/resolver db)
   :resolver-categories (categories/resolver db)})
