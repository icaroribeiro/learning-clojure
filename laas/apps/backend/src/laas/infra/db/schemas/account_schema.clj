(ns laas.infra.db.schemas.account-schema)

(def schema
  [
   {:db/ident       :account/id
    :db/unique      :db.unique/identity
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc         "External UUID for the wallet/account"}

   {:db/ident       :account/type
    :db/valueType   :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/doc         "Type: :individual, :corporate, :system, or :drex-pool"}

   {:db/ident       :account/status
    :db/valueType   :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/doc         "Status: :active, :blocked, :pending-kyc"}])