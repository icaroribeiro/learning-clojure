# Target: sync-deps

sync-deps:
		lein deps

# Target: run-dev
# Purpose: Run the main application in development mode
# Starts the Python application directly for local development and debugging
run-dev:
		python app/main.py

# Target: test-dev
# Purpose: Execute the test suite in development with code coverage reporting
# Runs pytest, measures coverage for the 'app' package, and shows missing coverage lines
test-dev:
		pytest --cov=app --cov-report=term-missing

# Target: gen-migration
# Purpose: Generate a new Alembic migration script based on model changes
# Requires the FILE variable (e.g., make gen-migration FILE="add user table") to set the migration message
gen-migration:
		alembic revision --autogenerate -m $(FILE)

# Target: upgrade-db
# Purpose: Apply all pending database migrations to bring the schema up to date
# Runs Alembic to upgrade the database to the latest revision (head)
upgrade-db:
		alembic upgrade head

# Target: downgrade-db
# Purpose: Roll back the last applied database migration
# Useful for undoing the most recent change during development or fixing issues
downgrade-db:
		alembic downgrade -1