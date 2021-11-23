#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE product_catalog;
    GRANT ALL PRIVILEGES ON DATABASE product_catalog TO dbuser;

    CREATE DATABASE product_order;
    GRANT ALL PRIVILEGES ON DATABASE product_order TO dbuser;

        CREATE DATABASE shopping_cart;
        GRANT ALL PRIVILEGES ON DATABASE shopping_cart TO dbuser;
EOSQL
