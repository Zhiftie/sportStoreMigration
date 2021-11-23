CREATE TABLE public.order_line
(
    order_line_id bigserial NOT NULL,
    product_id bigint NOT NULL,
    quantity bigint NOT NULL,
    order_id bigint NOT NULL,
    PRIMARY KEY (order_line_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id)
    REFERENCES public."order" (order_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
);

ALTER TABLE IF EXISTS public.order_line
OWNER to dbuser;
