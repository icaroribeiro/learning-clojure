# Dockerfile for Datomic Pro infrastructure
# Builds a local image containing the Datomic Pro distribution for Transactor and Console use

# Base stage: Uses Clojure on Temurin (OpenJDK) for the runtime environment
# 'slim' variant reduces the attack surface and final image size
FROM clojure:temurin-25-tools-deps-trixie-slim

# Install system dependencies:
# - curl: Required to download the Datomic distribution from S3
# - unzip: Required to extract the Datomic Pro zip archive
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl unzip \
    && rm -rf /var/lib/apt/lists/*

# Set the specific Datomic Pro version to be used across the infrastructure
# Updating this version here will propagate to both Transactor and Console services
ENV DATOMIC_VERSION=1.0.7482

# Download and install Datomic Pro:
# 1. Downloads the distribution directly from Cognitect's S3 bucket
# 2. Unzips the archive to the build context
# 3. Moves the extracted folder to a standardized location (/usr/datomic-pro)
# 4. Cleans up the zip file to reduce image layer size
RUN curl https://datomic-pro-downloads.s3.amazonaws.com/${DATOMIC_VERSION}/datomic-pro-${DATOMIC_VERSION}.zip \
    -o datomic-pro.zip \
    && unzip datomic-pro.zip \
    && mv datomic-pro-${DATOMIC_VERSION} /usr/datomic-pro \
    && rm datomic-pro.zip

# Set the working directory to the Datomic root
# This allows bin/transactor and bin/console commands to find their relative dependencies
WORKDIR /usr/datomic-pro