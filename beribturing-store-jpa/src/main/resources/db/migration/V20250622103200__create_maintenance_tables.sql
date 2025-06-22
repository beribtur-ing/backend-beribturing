CREATE TABLE maintenance_request
(
    id                 VARCHAR(255)                              NOT NULL,
    entity_version     BIGINT                                    NOT NULL,
    registered_by      VARCHAR(255),
    modified_by        VARCHAR(255),
    registered_on      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    product_variant_id VARCHAR(255),
    requester_id       VARCHAR(255),
    owner_id           VARCHAR(255),
    rental_record_id   VARCHAR(255),
    description        TEXT,
    status             VARCHAR(255),
    requested_at       TIMESTAMP WITHOUT TIME ZONE,
    resolved_at        TIMESTAMP WITHOUT TIME ZONE,
    resolution         TEXT,
    CONSTRAINT pk_maintenance_request PRIMARY KEY (id)
);

CREATE TABLE maintenance_photo
(
    id                     VARCHAR(255)                              NOT NULL,
    entity_version         BIGINT                                    NOT NULL,
    registered_by          VARCHAR(255),
    modified_by            VARCHAR(255),
    registered_on          TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on            TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    maintenance_request_id VARCHAR(255),
    url                    VARCHAR(255),
    description            TEXT,
    photo_order            INTEGER                                   NOT NULL,
    CONSTRAINT pk_maintenance_photo PRIMARY KEY (id)
);