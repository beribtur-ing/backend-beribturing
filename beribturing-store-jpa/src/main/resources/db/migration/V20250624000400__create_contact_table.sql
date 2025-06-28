-- Create CONTACT table for managing user contact information

CREATE TABLE contact
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    user_id        VARCHAR(255)                              NOT NULL,
    email          VARCHAR(255),
    phone_number   VARCHAR(255),
    fcm_tokens     TEXT,
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

-- Create unique constraint on user_id to ensure one contact per user
ALTER TABLE contact
    ADD CONSTRAINT uc_contact_user_id UNIQUE (user_id);

-- Create indexes for better query performance
CREATE INDEX idx_contact_user_id ON contact (user_id);
CREATE INDEX idx_contact_email ON contact (email);
CREATE INDEX idx_contact_phone_number ON contact (phone_number);