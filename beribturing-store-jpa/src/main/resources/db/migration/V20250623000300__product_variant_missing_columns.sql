-- Add missing columns to PRODUCT_VARIANT table
ALTER TABLE product_variant
    ADD COLUMN image_sequence BIGINT;