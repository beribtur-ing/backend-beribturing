-- Add missing columns to PRODUCT_IMAGE table
ALTER TABLE product_image
    ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;