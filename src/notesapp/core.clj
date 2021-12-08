(ns notesapp.core
  (:require
    [dotenv :refer [env app-env]]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [org.httpkit.server :as server]
    [ring.middleware.json :as midjson]
    [ring.middleware.defaults :refer :all]
    [ring.middleware.reload :refer [wrap-reload]]
    [clojure.string :as str]
    [clojure.data.json :as json]
    [notesapp.lib.routes :as routes])
  (:gen-class))



(defroutes app-routes
  (GET "/notes" [] routes/get-notes)
  (POST "/notes" [] routes/add-note))


(defn -main
  [& args]
  (let [port (Integer/parseInt (env :PORT))]
    (server/run-server (midjson/wrap-json-params (midjson/wrap-json-response (wrap-defaults #'app-routes api-defaults))) {:port port})
    (println (str "Running on http://127.0.0.1:" port "/"))))


(defn -dev-main
  [& args]
  (let [port (Integer/parseInt (env :PORT))]
    (server/run-server (wrap-reload (midjson/wrap-json-params (midjson/wrap-json-response (wrap-defaults #'app-routes api-defaults)))) {:port port})
    (println (str "Running on http://127.0.0.1:" port "/"))))
