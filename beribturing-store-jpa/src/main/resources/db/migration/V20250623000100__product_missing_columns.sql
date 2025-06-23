-- Add missing columns to PRODUCT table
ALTER TABLE product
    ADD COLUMN variant_sequence BIGINT,
    ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;