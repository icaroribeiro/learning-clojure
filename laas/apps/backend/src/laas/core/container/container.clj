(ns laas.core.container.container
  (:require [com.stuartsierra.component :as component]
            [laas.infra.db.datomic :as datomic]
            [laas.core.settings.datomic-settings :as settings]))

(defn new-system []
  (component/system-map
   ;; 1. The Settings Component
   ;; Ensure this returns a map or record containing the :uri key
   :settings (settings/get-datomic-settings)

   ;; 2. The Database Component
   ;; 'using' tells Component to inject :settings into the :settings key of DatomicDatabase
   :db (component/using
        (datomic/new-datomic-db)
        [:settings])))