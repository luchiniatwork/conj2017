(defproject e-commerce-demo "0.1.0-SNAPSHOT"
  :description "FIXME: GraphQL API for e-commerce-demo"
  :url "FIXME: https://github.com/fixme"
  
  :dependencies [[camel-snake-kebab "0.4.0"]
                 [clojure-future-spec "1.9.0-alpha17"]
                 [com.netflix.hystrix/hystrix-clj "1.5.12"]
                 [com.stuartsierra/component "0.3.2"]
                 [com.walmartlabs/lacinia "0.20.0"]
                 [com.walmartlabs/lacinia-pedestal "0.3.0"]
                 [environ "1.1.0"]
                 [io.pedestal/pedestal.jetty "0.5.2"]
                 [io.pedestal/pedestal.log "0.5.2"]
                 [io.pedestal/pedestal.route "0.5.2"]
                 [io.pedestal/pedestal.service "0.5.2"]
                 [luchiniatwork/hystrix-event-stream-clj "0.3.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.danielsz/system "0.4.0"]
                 [org.omcljs/om "1.0.0-beta1"]
                 [org.slf4j/slf4j-simple "1.7.21"]
                 [umlaut "0.1.14-SNAPSHOT"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-umlaut "0.1.2-SNAPSHOT"]]

  :min-lein-version "2.6.1"

  ;; Starts server with a simple `lein run`
  :main ^:skip-aot e-commerce-demo.application

  :uberjar-name "e-commerce-demo.jar"
  
  :target-path "target/%s"

  ;; nREPL by default starts in the :main namespace, we want to start in `user`
  ;; because that's where our development helper functions like (go) and (reset)
  ;; live.
  :repl-options {:init-ns user}

  :bikeshed {:max-line-length 200}

  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.13"]
                                  [reloaded.repl "0.2.3"]]
                   :source-paths ["dev"]}
             :uberjar {:omit-source true
                       :aot :all}})
