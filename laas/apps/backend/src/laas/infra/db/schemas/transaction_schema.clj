(ns laas.infra.db.schemas.transaction-schema)

(def schema
  [
   {:db/ident       :tx-log/id
    :db/unique      :db.unique/identity
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc         "Internal Ledger Transaction ID"}

   {:db/ident       :tx-log/from
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Source Wallet"}

   {:db/ident       :tx-log/to
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Destination Wallet"}

   {:db/ident       :tx-log/amount
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Amount in BRL/Drex (BigDecimal)"} 
   
   {:db/ident       :tx-log/blockchain-hash
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "The hash of the transaction on the Drex/Blockchain network"}

   {:db/ident       :tx-log/status
    :db/valueType   :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/doc         "Status: :pending-on-chain, :confirmed, :failed"}])