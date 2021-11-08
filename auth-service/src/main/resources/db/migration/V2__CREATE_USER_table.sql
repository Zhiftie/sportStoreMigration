CREATE TABLE public."user"
(
    user_id bigserial NOT NULL,
    name text NOT NULL,
    password text NOT NULL,
    tenant text NOT NULL,
    PRIMARY KEY (user_id)
);

ALTER TABLE IF EXISTS public."user"
    OWNER to dbuser;
