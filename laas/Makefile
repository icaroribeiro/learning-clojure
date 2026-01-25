# Target: init-backend
# Purpose: Build (if needed) and start the backend service in detached mode using docker-compose
# Starts the application server/API container independently of other services
init-backend:
			docker-compose up --build -d backend

# Target: init-datomic
# Purpose: Start the Datomic Transactor and the Visual Console
# This brings up the database engine and the UI for data exploration
init-datomic:
			docker-compose up -d datomic-transactor datomic-console

# Target: init-all
# Purpose: Start all services (backend and datomic) together
# Convenience target to bring the entire stack online with a single command
init-all:	init-backend init-datomic

# Target: finish-backend
# Purpose: Stop and remove the backend service container, including associated volumes
# Cleans up backend resources without affecting other running services
finish-backend:
			docker-compose down -v backend

# Target: finish-datomic
# Purpose: Stop and remove the datomic service container, including associated volumes
# Removes the database container and deletes persistent data (use with caution)
finish-datomic:
			docker-compose down -v datomic-transactor datomic-console

# Target: finish-all
# Purpose: Stop and remove all services (backend and datomic) and their volumes
# Fully tears down the entire stack and cleans up all resources in one command
finish-all: finish-backend finish-datomic