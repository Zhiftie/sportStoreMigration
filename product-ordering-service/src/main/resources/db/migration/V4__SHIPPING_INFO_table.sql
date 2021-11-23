CREATE TABLE public.shipping_info
(
    shipping_info_id bigserial NOT NULL,
    order_id bigint NOT NULL,
    name text,
    line1 text,
    line2 text,
    line3 text,
    city text,
    state text,
    country text,
    gift_wrap boolean,
    shipped boolean,
    PRIMARY KEY (shipping_info_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id)
    REFERENCES public."orders" (order_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID
);

ALTER TABLE IF EXISTS public.shipping_info
        OWNER to dbuser;
