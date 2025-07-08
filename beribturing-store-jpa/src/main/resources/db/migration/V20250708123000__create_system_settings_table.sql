-- Create SYSTEM_SETTINGS table for managing system configuration

CREATE TABLE system_settings
(
    id                                 VARCHAR(255)                              NOT NULL,
    entity_version                     BIGINT                                    NOT NULL,
    registered_by                      VARCHAR(255),
    modified_by                        VARCHAR(255),
    registered_on                      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on                        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    platform_name                      VARCHAR(255)                              NOT NULL,
    support_email                      VARCHAR(255)                              NOT NULL,
    platform_description               VARCHAR(1000),
    maintenance_mode                   BOOLEAN                                   NOT NULL DEFAULT FALSE,
    -- PaymentSettings
    commission_rate                    INTEGER                                   NOT NULL DEFAULT 0,
    default_currency                   VARCHAR(255),
    public_key                         VARCHAR(255),
    enable_automatic_payouts           BOOLEAN                                   NOT NULL DEFAULT FALSE,
    -- NotificationSettings
    email_notifications                BOOLEAN                                   NOT NULL DEFAULT TRUE,
    sms_notifications                  BOOLEAN                                   NOT NULL DEFAULT FALSE,
    push_notifications                 BOOLEAN                                   NOT NULL DEFAULT TRUE,
    notification_email_template        VARCHAR(255),
    -- SecuritySettings
    session_timout_minutes             INTEGER                                   NOT NULL DEFAULT 30,
    max_login_attempts                 INTEGER                                   NOT NULL DEFAULT 5,
    require_two_factor_authentication  BOOLEAN                                   NOT NULL DEFAULT FALSE,
    enforce_password_complexity        BOOLEAN                                   NOT NULL DEFAULT TRUE,
    enable_audit_logging               BOOLEAN                                   NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_system_settings PRIMARY KEY (id)
);