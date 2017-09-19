(ns e-commerce-demo.components.routes)

(defn ^:interceptor-fn respond-hello [request]
  {:status 200 :body "Hello, world!!!!!"})

(def routes
  #{["/greet" :get respond-hello :route-name :greet]})
