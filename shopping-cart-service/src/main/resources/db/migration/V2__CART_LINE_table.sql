CREATE TABLE public."cart_line"
(
    cart_line_id bigserial NOT NULL,
    product_id bigint NOT NULL,
    quantity bigint NOT NULL,
    PRIMARY KEY (cart_line_id)
);

ALTER TABLE IF EXISTS public."cart_line"
    OWNER to dbuser;
