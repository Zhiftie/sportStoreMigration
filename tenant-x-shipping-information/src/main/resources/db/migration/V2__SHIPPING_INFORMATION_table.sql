CREATE TABLE public.shipping_information
(
    shipping_information_id bigserial NOT NULL,
    arrival_time timestamp without time zone NOT NULL,
    shipping_time timestamp without time zone NOT NULL,
    order_id bigint NOT NULL,
    PRIMARY KEY (shipping_information_id)
);

ALTER TABLE IF EXISTS public.shipping_information
    OWNER to dbuser;
