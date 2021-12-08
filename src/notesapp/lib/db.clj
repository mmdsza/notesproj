(ns notesapp.lib.db
  (:require
    [dotenv :refer [env app-env]]
    [clojure.java.jdbc :as jdbc]
    [clojure.string :as str])
  (:gen-class))


(def -db
  {:dbtype "postgresql"
   :dbname (env :DB_NAME)
   :host (env :DB_HOST)
   :user (env :DB_USER)
   :password (env :DB_PASS)})


(defn concat-fields
  [fields]
  (str/join ", " (map name fields)))

(defn insert
  [table record]
  (first (jdbc/insert! -db table record)))


(defn select
  [table fields]
  (jdbc/query -db [(str "select " (concat-fields fields) " from " (name table))]))
