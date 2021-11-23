CREATE TABLE public."order"
(
    order_id bigserial NOT NULL,
    customer_id text NOT NULL,
    total_cost double precision NOT NULL,
    PRIMARY KEY (order_id)
);

ALTER TABLE IF EXISTS public."order"
    OWNER to dbuser;
