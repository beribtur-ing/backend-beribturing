-- Add missing columns to RENTAL_RECORD table
ALTER TABLE rental_record
    ADD COLUMN owner_id VARCHAR(255) NOT NULL DEFAULT '';