(ns notesapp.lib.routes
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [clojure.string :as str]
    [clojure.data.json :as json]
    [notesapp.lib.api :as api])
  (:gen-class))



(defn get-notes
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (api/get-notes))})


(defn add-note
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (api/add-note (req :params)))})
