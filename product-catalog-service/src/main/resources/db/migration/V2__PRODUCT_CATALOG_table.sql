CREATE TABLE IF NOT EXISTS public.product_catalog
(
    product_id bigserial NOT NULL,
    description text,
    product_name text NOT NULL,
    product_category text NOT NULL,
    price bigint NOT NULL,
    PRIMARY KEY (product_id)
);

ALTER TABLE IF EXISTS public.product_catalog
    OWNER to dbuser;
