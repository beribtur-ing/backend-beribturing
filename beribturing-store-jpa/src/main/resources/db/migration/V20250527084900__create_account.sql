CREATE TABLE account
(
    id                      VARCHAR(255)                              NOT NULL,
    entity_version          BIGINT                                    NOT NULL,
    registered_by           VARCHAR(255),
    modified_by             VARCHAR(255),
    registered_on           TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on             TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    phone_number            VARCHAR(255)                              NOT NULL,
    password                VARCHAR(255)                              NOT NULL,
    email                   VARCHAR(255),
    role                    VARCHAR(255)                              NOT NULL,
    enabled                 BOOLEAN                                   NOT NULL,
    account_non_expired     BOOLEAN                                   NOT NULL,
    account_non_locked      BOOLEAN                                   NOT NULL,
    credentials_non_expired BOOLEAN                                   NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT uc_account_phonenumber UNIQUE (phone_number);