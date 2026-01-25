(ns laas.core
  (:require [com.stuartsierra.component :as component]
            [laas.core.container.container :as container]
            [laas.infra.db.schemas.account-schema :as acc]
            [laas.infra.db.schemas.transaction-schema :as tx]
            [datomic.api :as d])
  (:gen-class))

(defn run-migrations []
  (println "Initializing system for migrations...")
  ;; Start the system to get a managed connection
  (let [system (component/start (container/new-system))
        conn   (get-in system [:db :conn])
        schema (vec (concat acc/schema tx/schema))]
    (try
      (println "Applying Ledger Schema...")
      @(d/transact conn schema)
      (println "Database updated successfully.")
      (catch Exception e
        (println "Migration failed!")
        (throw e))
      (finally
        ;; Always stop the system to release resources
        (component/stop system)))))

(defn start-app []
  (println "Starting Application...")
  (let [system (component/start (container/new-system))]
    ;; We use 'system' here in the shutdown hook, so the linter error disappears!
    (.addShutdownHook (Runtime/getRuntime)
                      (Thread. #(do (println "\nShutting down...")
                                    (component/stop system))))
    (println "Server is running. Press Ctrl+C to stop.")
    @(promise)))

(defn -main [& args]
  (let [command (first args)]
    (try
      (case command
        "migrate" (run-migrations)
        "seed"    (println "Seed logic would go here")
        (start-app))
      (catch Exception e
        (println "Error occurred:" (.getMessage e))
        (.printStackTrace e)
        (System/exit 1))
      (finally
        (shutdown-agents)
        (println "Exiting...")
        ;; Only exit if we ran a short-lived command
        (when (#{"migrate" "seed"} command)
          (System/exit 0))))))