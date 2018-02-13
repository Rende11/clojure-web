(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]))

(defn welcome
  "Ring base handler"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure world!!!!</h1>"
     :headers {}}
    {:status 404
     :body "<h1>Requested page not found"
     :headers {}}))

(defn goodbye
  "Goodbye"
  [request]
  {:status 200
   :body "<h1>BB</h1>"
   :headers {}})

(defn about
  "Information about developer"
  [request]
  {:status 200
   :body "Get started learn clojure"
   :headers {}})

(defn hello
  "Personal greet"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Hello " name ".")
     :headers {}}))

(def operands {"+" + "-" - "*" * ":" /})

(defn calc
  "Calculate"
  [request]
  (let [a (Integer. (get-in request [:route-params :a]))
        b (Integer. (get-in request [:route-params :b]))
        op (get-in request [:route-params :op])
        f (get operands op)]
   (if f
     {:status 200
      :body (str "Calculated: " (f a b))
      :headers {}}
     {:status 404
      :body "Unknown operator"
      :headers {}})))

(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/request-info" [] handle-dump)
  (GET "/hello/:name" [] hello)
  (GET "/calc/:a/:op/:b" [] calc)
  (not-found "<h1>Page not found</h1>"))


(defn -main
  "Simple web server Ring and Jetty"
  [port]
  (jetty/run-jetty
    app
    {:port (Integer. port)}))


(defn -dev-main
  "Simple web server Ring and Jetty"
  [port]
  (jetty/run-jetty
    (wrap-reload #'app)
    {:port (Integer. port)}))


