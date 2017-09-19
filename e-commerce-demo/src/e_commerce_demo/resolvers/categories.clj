(ns e-commerce-demo.resolvers.categories)

(defn resolver
  [context arguments value]
  [{:id "id"
    :name "bla"
    :category {:id "cat"
               :name "cate"}
    :image "img"}])
