(ns laas.infra.db.datomic
  (:require [com.stuartsierra.component :as component]
            [datomic.api :as d]))

;; Define the Record to hold the state
(defrecord DatomicDatabase [settings conn]
  component/Lifecycle
  
  (start [this]
    ;; We check if conn already exists to prevent double-starting
    (if conn
      this
      (let [uri (:uri settings) ; Provided by the settings component
            _ (println "Connecting to Datomic at:" uri)
            _ (d/create-database uri)
            connection (d/connect uri)]
        (assoc this :conn connection))))

  (stop [this]
    (if conn
      (do
        (println "Closing Datomic connection...")
        ;; Datomic Peer connections don't strictly require .close, 
        ;; but we clear the atom to be lifecycle-compliant.
        (assoc this :conn nil))
      this)))

;; Constructor function
(defn new-datomic-db []
  (map->DatomicDatabase {}))