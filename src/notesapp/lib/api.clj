(ns notesapp.lib.api
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [clojure.string :as str]
    [clojure.data.json :as json]
    [notesapp.lib.db :as db])
  (:gen-class))


(defn get-notes
  []
  (db/select :notes [:id :title :context]))


(defn add-note
  [{title :title context :context :as record}]
  (db/insert :notes record))
