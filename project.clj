(defproject todo-list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [compojure "1.3.4"]
                 [environ "1.0.0"]]
  :main todo-list.core
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "todo-list-standalone.jar"
  :aot [todo-list.core]
  :profiles {:dev
                {:main todo-list.core/-dev-main}})
