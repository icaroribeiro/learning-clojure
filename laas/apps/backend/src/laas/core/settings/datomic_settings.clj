(ns laas.core.settings.datomic-settings
  (:require [dotenv :refer [env]]
            [malli.core :as m]
            [malli.error :as me]
            [malli.transform :as mt]))

(def ^:private DatomicSettingsSchema
  [:map
   [:protocol {:default "dev"} :string]
   [:username {:default "admin"} :string]
   [:password {:default "admin"} :string]
   [:host     {:default "localhost"} :string]
   [:port     {:default "4334"} :string]
   [:db-name  {:default "laas-db"} :string]])

(defn- build-uri [{:keys [protocol host port db-name username password]}]
  (if (and username password (not= username "admin"))
    (str "datomic:" protocol "://" username ":" password "@" host ":" port "/" db-name)
    (str "datomic:" protocol "://" host ":" port "/" db-name)))

(defn- load-settings []
  (let [
        raw-data {:protocol (env :DATOMIC_PROTOCOL)
                  :host     (env :DATOMIC_HOST)
                  :port     (env :DATOMIC_PORT)
                  :db-name  (env :DATOMIC_DB_NAME) 
                  :username (env :DATOMIC_USERNAME)
                  :password (env :DATOMIC_PASSWORD)}
        
        cleaned (into {} (remove (comp nil? val) raw-data))
        
        settings (m/decode DatomicSettingsSchema 
                           cleaned 
                           (mt/default-value-transformer))]
    
    (if (m/validate DatomicSettingsSchema settings)
      (assoc settings :uri (build-uri settings))
      (throw (ex-info "Invalid Datomic Settings"
                      {:errors (me/humanize (m/explain DatomicSettingsSchema settings))})))))

(def get-datomic-settings 
  (memoize load-settings))