CREATE TABLE public.saved_events
(
    saved_event_id bigserial NOT NULL,
    json text,
    PRIMARY KEY (saved_event_id)
);

ALTER TABLE IF EXISTS public.saved_events
    OWNER to dbuser;
