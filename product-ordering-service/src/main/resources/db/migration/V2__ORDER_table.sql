CREATE TABLE public."orders"
(
    order_id bigserial NOT NULL,
    customer_id text NOT NULL,
    total_cost double precision NOT NULL,
    PRIMARY KEY (order_id)
);

ALTER TABLE IF EXISTS public."orders"
    OWNER to dbuser;
