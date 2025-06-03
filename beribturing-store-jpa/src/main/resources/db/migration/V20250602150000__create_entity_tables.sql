-- CHAT_MESSAGE
CREATE TABLE chat_message
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    sender_id      VARCHAR(255)                              NOT NULL,
    receiver_id    VARCHAR(255)                              NOT NULL,
    content        VARCHAR(255)                              NOT NULL,
    timestamp      TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    read           BOOLEAN                                   NOT NULL,
    CONSTRAINT pk_chat_message PRIMARY KEY (id)
);

-- PRODUCT_CATEGORY
CREATE TABLE product_category
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    name           VARCHAR(255)                              NOT NULL,
    description    VARCHAR(255),
    parent_id      VARCHAR(255),
    CONSTRAINT pk_product_category PRIMARY KEY (id)
);

-- PRODUCT_IMAGE
CREATE TABLE product_image
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    variant_id     VARCHAR(255)                              NOT NULL,
    url            VARCHAR(255)                              NOT NULL,
    "order"        INTEGER                                   NOT NULL,
    CONSTRAINT pk_product_image PRIMARY KEY (id)
);

-- PRODUCT
CREATE TABLE product
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    owner_id       VARCHAR(255)                              NOT NULL,
    title          VARCHAR(255)                              NOT NULL,
    description    VARCHAR(255),
    category_id    VARCHAR(255),
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- PRODUCT_VARIANT
CREATE TABLE product_variant
(
    id                VARCHAR(255)                              NOT NULL,
    entity_version    BIGINT                                    NOT NULL,
    registered_by     VARCHAR(255),
    modified_by       VARCHAR(255),
    registered_on     TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on       TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    product_id        VARCHAR(255)                              NOT NULL,
    price_amount      DECIMAL,
    price_currency    VARCHAR(255),
    price_unit        VARCHAR(255),
    size_label        VARCHAR(255),
    size_width        DOUBLE PRECISION,
    size_height       DOUBLE PRECISION,
    size_depth        DOUBLE PRECISION,
    size_weight       DOUBLE PRECISION,
    size_measure_unit VARCHAR(255),
    color             VARCHAR(255),
    brand             VARCHAR(255),
    model             VARCHAR(255),
    manufacturer      VARCHAR(255),
    made_in           VARCHAR(255),
    produced_year     VARCHAR(255),
    material          VARCHAR(255),
    manual            VARCHAR(255),
    available_from    TIMESTAMP WITHOUT TIME ZONE,
    available_until   TIMESTAMP WITHOUT TIME ZONE,
    available_days    VARCHAR(255),
    active            BOOLEAN                                   NOT NULL,
    notes             VARCHAR(255),
    CONSTRAINT pk_product_variant PRIMARY KEY (id)
);

-- NOTIFICATION
CREATE TABLE notification
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    recipient_id   VARCHAR(255)                              NOT NULL,
    message        VARCHAR(255)                              NOT NULL,
    type           VARCHAR(255)                              NOT NULL,
    read           BOOLEAN                                   NOT NULL,
    timestamp      TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

-- DISCOUNT
CREATE TABLE discount
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    name           VARCHAR(255)                              NOT NULL,
    type           VARCHAR(255)                              NOT NULL,
    amount         DECIMAL                                   NOT NULL,
    scope          VARCHAR(255)                              NOT NULL,
    target_id      VARCHAR(255),
    start_date     TIMESTAMP WITHOUT TIME ZONE,
    end_date       TIMESTAMP WITHOUT TIME ZONE,
    active         BOOLEAN                                   NOT NULL,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

-- RENTAL_DEPOSIT
CREATE TABLE rental_deposit
(
    id               VARCHAR(255)                              NOT NULL,
    entity_version   BIGINT                                    NOT NULL,
    registered_by    VARCHAR(255),
    modified_by      VARCHAR(255),
    registered_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    rental_record_id VARCHAR(255)                              NOT NULL,
    payer_id         VARCHAR(255)                              NOT NULL,
    amount           DECIMAL                                   NOT NULL,
    currency         VARCHAR(255)                              NOT NULL,
    status           VARCHAR(255)                              NOT NULL,
    paid_at          TIMESTAMP WITHOUT TIME ZONE,
    resolved_at      TIMESTAMP WITHOUT TIME ZONE,
    notes            VARCHAR(255),
    CONSTRAINT pk_rental_deposit PRIMARY KEY (id)
);

-- TRANSACTION
CREATE TABLE transaction
(
    id                  VARCHAR(255)                              NOT NULL,
    entity_version      BIGINT                                    NOT NULL,
    registered_by       VARCHAR(255),
    modified_by         VARCHAR(255),
    registered_on       TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on         TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    rental_record_id    VARCHAR(255)                              NOT NULL,
    payer_id            VARCHAR(255)                              NOT NULL,
    payee_id            VARCHAR(255)                              NOT NULL,
    total_amount        DECIMAL                                   NOT NULL,
    total_currency      VARCHAR(255)                              NOT NULL,
    commission_amount   DECIMAL,
    commission_currency VARCHAR(255),
    payee_amount        DECIMAL,
    payee_currency      VARCHAR(255),
    status              VARCHAR(255)                              NOT NULL,
    initiated_at        TIMESTAMP WITHOUT TIME ZONE,
    completed_at        TIMESTAMP WITHOUT TIME ZONE,
    payment_provider    VARCHAR(255),
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

-- ITEM_CONDITION_CHECK
CREATE TABLE item_condition_check
(
    id               VARCHAR(255)                              NOT NULL,
    entity_version   BIGINT                                    NOT NULL,
    registered_by    VARCHAR(255),
    modified_by      VARCHAR(255),
    registered_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    rental_record_id VARCHAR(255)                              NOT NULL,
    variant_id       VARCHAR(255)                              NOT NULL,
    checked_by       VARCHAR(255)                              NOT NULL,
    check_type       VARCHAR(255)                              NOT NULL,
    CONSTRAINT pk_item_condition_check PRIMARY KEY (id)
);

-- ITEM_CONDITION_PHOTO
CREATE TABLE item_condition_photo
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    check_id       VARCHAR(255)                              NOT NULL,
    url            VARCHAR(255)                              NOT NULL,
    CONSTRAINT pk_item_condition_photo PRIMARY KEY (id)
);

-- RENTAL_RECORD
CREATE TABLE rental_record
(
    id                 VARCHAR(255)                              NOT NULL,
    entity_version     BIGINT                                    NOT NULL,
    registered_by      VARCHAR(255),
    modified_by        VARCHAR(255),
    registered_on      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    start_date_time    TIMESTAMP WITHOUT TIME ZONE,
    end_date_time      TIMESTAMP WITHOUT TIME ZONE,
    rented_at          TIMESTAMP WITHOUT TIME ZONE,
    returned_at        TIMESTAMP WITHOUT TIME ZONE,
    cancelled_at       TIMESTAMP WITHOUT TIME ZONE,
    product_variant_id VARCHAR(255)                              NOT NULL,
    status             VARCHAR(255)                              NOT NULL,
    lendee_id          VARCHAR(255)                              NOT NULL,
    fee_amount         DECIMAL,
    fee_currency       VARCHAR(255),
    discount_id        VARCHAR(255),
    deposit_id         VARCHAR(255),
    CONSTRAINT pk_rental_record PRIMARY KEY (id)
);

-- RESERVATION
CREATE TABLE reservation
(
    id                 VARCHAR(255)                              NOT NULL,
    entity_version     BIGINT                                    NOT NULL,
    registered_by      VARCHAR(255),
    modified_by        VARCHAR(255),
    registered_on      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    product_variant_id VARCHAR(255)                              NOT NULL,
    requester_id       VARCHAR(255)                              NOT NULL,
    start_date_time    TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    end_date_time      TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    status             VARCHAR(255)                              NOT NULL,
    note               VARCHAR(1000),
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

-- REPORT
CREATE TABLE report
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    reporter_id    VARCHAR(255)                              NOT NULL,
    reason         VARCHAR(1000)                             NOT NULL,
    report_date    TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    resolved       BOOLEAN                                   NOT NULL,
    record_id      VARCHAR(255)                              NOT NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
);

-- REVIEW
CREATE TABLE review
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    reviewer_id    VARCHAR(255)                              NOT NULL,
    rating         INTEGER                                   NOT NULL,
    comment        VARCHAR(1000),
    record_id      VARCHAR(255)                              NOT NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

-- LENDEE
CREATE TABLE lendee
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    name           VARCHAR(255)                              NOT NULL,
    phone_number   VARCHAR(255)                              NOT NULL,
    active         BOOLEAN                                   NOT NULL,
    gender         VARCHAR(255),
    email          VARCHAR(255),
    address        VARCHAR(255),
    avatar_url     VARCHAR(255),
    latitude       DOUBLE PRECISION,
    longitude      DOUBLE PRECISION,
    CONSTRAINT pk_lendee PRIMARY KEY (id)
);

ALTER TABLE lendee
    ADD CONSTRAINT uc_lendee_phonenumber UNIQUE (phone_number);

-- LENDER
CREATE TABLE lender
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    name           VARCHAR(255)                              NOT NULL,
    phone_number   VARCHAR(255)                              NOT NULL,
    lender_type    VARCHAR(255)                              NOT NULL,
    active         BOOLEAN                                   NOT NULL,
    gender         VARCHAR(255),
    email          VARCHAR(255),
    address        VARCHAR(255),
    avatar_url     VARCHAR(255),
    latitude       DOUBLE PRECISION,
    longitude      DOUBLE PRECISION,
    CONSTRAINT pk_lender PRIMARY KEY (id)
);

ALTER TABLE lender
    ADD CONSTRAINT uc_lender_phonenumber UNIQUE (phone_number);
