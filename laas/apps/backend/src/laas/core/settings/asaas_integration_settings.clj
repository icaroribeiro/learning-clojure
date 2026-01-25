;; (ns laas.core.settings.asaas-integration-settings
;;   (:require [environ.core :refer [env]] ; Reads .env / system vars
;;             [malli.core :as m]        ; Core validation engine
;;             [malli.error :as me]      ; Turns cryptic errors into human text
;;             [malli.util :as mu]))     ; Utility for decoding/defaults  


;; (def ^:private AsaasIntegrationSettingsSchema
;;   [:map
;;    [:api-key {:optional false} :string]
;;    [:api-url {:default "https://sandbox.asaas.com/api/v3"} :string]
;;    [:webhook-token {:optional true} :string]])

;; (defn- load-settings []
;;   (let [raw-data {:api-key (env :asaas-integration-api-key)
;;                   :api-url (env :asaas-integration-api-url)
;;                   :webhook-token (env :asaas--integration-webhook-token)}
;;         ; It removes any keys that have a nil value. We do this because Malli only applies defaults if the key is completely missing from the map.
;;         cleaned (into {} (remove (comp nil? val) raw-data))
;;         ; Malli takes your "cleaned" map, compares it to the schema, and injects the default values for any missing fields.
;;         settings (mu/decode AsaasIntegrationSettingsSchema cleaned (mu/default-transformer))]

;;     ; Returns true if the data matches the schema.
;;     (if (m/validate AsaasIntegrationSettingsSchema settings)
;;       settings
;;       (throw (ex-info "Invalid Asaas Integration Configuration"
;;                       {:errors (me/humanize (m/explain AsaasIntegrationSettingsSchema settings))})))))

;; ; By wrapping load-settings in memoize, Clojure runs the code once, remembers the result, and gives you that same result instantly every other time you call it.
;; (def get-asaas-integration-settings 
;;   (memoize load-settings))