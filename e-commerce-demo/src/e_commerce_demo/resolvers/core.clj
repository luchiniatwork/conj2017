(ns e-commerce-demo.resolvers.core
  (:require [e-commerce-demo.resolvers.categories :as categories]
            [e-commerce-demo.resolvers.image :as image]
            [e-commerce-demo.resolvers.products :as products]))

(defn build-resolvers [{:keys [db delayer-prods delayer-cats]}]
  {:resolver-products (products/resolver (:db db) (:delayer delayer-prods))
   :resolver-categories (categories/resolver (:db db) (:delayer delayer-cats))
   :resolver-image image/resolver})
