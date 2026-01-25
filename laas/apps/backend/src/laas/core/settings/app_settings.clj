(ns laas.core.settings.app-settings
  (:require [environ.core :refer [env]] ; Reads .env / system vars
            [malli.core :as m]
            [malli.error :as me]
            [malli.transform :as mt]))

(def ^:private AppSettingsSchema
  [:map
   [:my-secret    {:default "your_my_secret_here"} :string]
   [:api-host     {:default "your_api_host_here"} :string]
   [:api-port     {:default "your_api_port_here"} :string]
   [:frontend-url {:default "your_frontend_host_here"} :string]
   [:render-frontend-host {:default "your_render_frontend_host_here"} :string]])

(defn- load-settings []
  (let [raw-data {:my-secret    (env :app-my-secret)
                  :api-host     (env :app-api-host)
                  :api-port     (env :app-api-port)
                  :frontend-url (env :app-frontend-url)
                  :render-frontend-host (env :app-render-frontend-host)}
        ; It removes any keys that have a nil value. We do this because Malli only applies defaults if the key is completely missing from the map.
        cleaned (into {} (remove (comp nil? val) raw-data))
        ; Malli takes your "cleaned" map, compares it to the schema, and injects the default values for any missing fields.
        settings (m/decode AppSettingsSchema 
                           cleaned
                           (mt/default-value-transformer))]
    
    ; Returns true if the data matches the schema.
    (if (m/validate AppSettingsSchema settings)
      settings
      (throw (ex-info "Invalid Application Settings" 
                      {:errors (me/humanize (m/explain AppSettingsSchema settings))})))))

; By wrapping load-settings in memoize, Clojure runs the code once, remembers the result, and gives you that same result instantly every other time you call it.
(def get-app-settings 
  (memoize load-settings))