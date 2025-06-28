-- Update CONTACT table to support @ElementCollection for FCM tokens

-- Drop the old fcm_tokens TEXT column
ALTER TABLE contact DROP COLUMN IF EXISTS fcm_tokens;

-- Create the separate table for FCM tokens (JPA @ElementCollection will manage this)
-- This table will be automatically created by JPA with the naming convention: contact_fcm_tokens
-- But we'll create it explicitly to ensure proper constraints and indexes

CREATE TABLE contact_fcm_tokens
(
    contact_id  VARCHAR(255) NOT NULL,
    fcm_tokens  TEXT         NOT NULL,
    CONSTRAINT fk_contact_fcm_tokens_contact FOREIGN KEY (contact_id) REFERENCES contact (id) ON DELETE CASCADE
);

-- Create index for better query performance
CREATE INDEX idx_contact_fcm_tokens_contact_id ON contact_fcm_tokens (contact_id);
CREATE INDEX idx_contact_fcm_tokens_token ON contact_fcm_tokens (fcm_tokens);
