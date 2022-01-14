CREATE TABLE IF NOT EXISTS public.rfid_tag
(
    rfid_id bigserial NOT NULL,
    saved_event_id bigint NOT NULL,
    PRIMARY KEY (rfid_id)
);

ALTER TABLE IF EXISTS public.rfid_tag
    OWNER to dbuser;
