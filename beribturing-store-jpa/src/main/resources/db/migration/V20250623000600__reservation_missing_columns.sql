-- Add missing columns to RESERVATION table
ALTER TABLE reservation
    ADD COLUMN owner_id VARCHAR(255) NOT NULL DEFAULT '';