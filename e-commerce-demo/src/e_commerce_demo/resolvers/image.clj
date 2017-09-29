(ns e-commerce-demo.resolvers.image
  (:require [e-commerce-demo.services.image :as image]))

(defn resolver
  [context arguments value]
  (let [width (or (:width arguments) 3200)
        height (or (:height arguments) 3200)
        image-obj (:imageObj value)]
    (image/parse-image-params image-obj height width)))
