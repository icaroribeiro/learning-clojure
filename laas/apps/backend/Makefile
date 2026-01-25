# Target: sync-deps
# Purpose: Download all Clojure dependencies defined in project.clj
sync-deps:
			lein deps

# Target: run-dev
# Purpose: Run the main application in development mode
# Calls the -main function in the namespace defined in project.clj (:main laas.core)
run-dev:
			lein run

# Target: repl
# Purpose: Start a REPL (Read-Eval-Print Loop) for interactive development
repl:
			lein repl

# Target: test-dev
# Purpose: Execute the test suite with code coverage reporting
test-dev:
			lein cloverage

# Target: upgrade-db
# Purpose: Apply Datomic schema migrations
migrate-db:
			lein run migrate

# Target: show-db
# Purpose: Provide the URL to access the Datomic Console
show-db:
			@echo "Datomic Console: http://localhost:9091/browse"