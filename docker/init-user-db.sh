#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE product_catalog;
    GRANT ALL PRIVILEGES ON DATABASE product_catalog TO dbuser;

    CREATE DATABASE user;
    GRANT ALL PRIVILEGES ON DATABASE user TO dbuser;
EOSQL
