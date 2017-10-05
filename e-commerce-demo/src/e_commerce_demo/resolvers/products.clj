(ns e-commerce-demo.resolvers.products
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [com.netflix.hystrix.core :refer [defcommand]]
            [e-commerce-demo.query :as query]))

(defcommand fetch-products
  {:hystrix/fallback-fn (fn [db q pred delayer] {:products/products []})}
  [db q pred delayer]
  (query/query db q {:products/products pred} delayer))

(defn resolver [db delayer]
  (fn [context arguments value]
    (let [q `[{:products/products ~(query/selectors context)}]
          pred (if (nil? arguments) identity #(= (:db/id %) (:byId arguments)))
          {:keys [products/products]} (fetch-products db q pred delayer)]
      (transform-keys ->camelCaseKeyword products))))
