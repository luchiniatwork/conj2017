(ns e-commerce-demo.resolvers.categories
  (:require [camel-snake-kebab.core :refer [->camelCaseKeyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [com.netflix.hystrix.core :refer [defcommand]]
            [e-commerce-demo.query :as query]))

(defcommand fetch-categories
  {:hystrix/fallback-fn (fn [db q pred delayer] {:categories/categories []})}
  [db q pred delayer]
  (query/query db q {:categories/categories pred} delayer))

(defn resolver [db delayer]
  (fn [context arguments value]
    (let [q `[{:categories/categories ~(query/selectors context)}]
          pred (if (nil? arguments) identity #(= (:db/id %) (:byId arguments)))
          {:keys [categories/categories]} (fetch-categories db q pred delayer)]
      (transform-keys ->camelCaseKeyword categories))))
