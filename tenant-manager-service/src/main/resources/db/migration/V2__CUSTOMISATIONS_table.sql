CREATE TABLE public.customisations
(
    customisation_id bigserial NOT NULL,
    tenant_name text NOT NULL,
    event_name text NOT NULL,
    endpoint text,
    PRIMARY KEY (customisation_id)
);

ALTER TABLE IF EXISTS public.customisations
    OWNER to dbuser;
