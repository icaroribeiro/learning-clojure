(defproject backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 ;; Read the .env and system variables
                 [environ "1.2.0"]
                 ;; Read the .env and push the values into the system environment
                 [lynxeyes/dotenv "1.1.0"]
                 ;; Validation and defaults
                 [metosin/malli "0.20.0"]
                 ;; For JSON processing
                 [cheshire "5.11.0"]
                 ;; The core Pedestal service library
                 [io.pedestal/pedestal.service "0.8.1"]
                 ;; The Jetty adapter to actually run the HTTP server
                 [io.pedestal/pedestal.jetty "0.8.1"]
                 ;; Pedestal requires a logging implementation to start properly
                 [ch.qos.logback/logback-classic "1.3.15"]
                 ;;
                 [com.stuartsierra/component "1.1.0"]
                 ;; Datomic
                 [com.datomic/peer "1.0.7075"]
                 ;; Migration l;ibrary
                 [io.rkn/conformity "0.5.4"]]
  
:plugins [[lein-environ "1.2.0"]
          ;; Plugin for test coverage
          [lein-cloverage "1.2.4"]]

  :main ^:skip-aot laas.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
