#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE product_catalog;
    GRANT ALL PRIVILEGES ON DATABASE product_catalog TO dbuser;
EOSQL
