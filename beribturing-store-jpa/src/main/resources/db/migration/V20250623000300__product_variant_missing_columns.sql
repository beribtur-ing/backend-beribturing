-- Add missing columns to PRODUCT_VARIANT table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'product_variant' AND column_name = 'image_sequence') THEN
        ALTER TABLE product_variant ADD COLUMN image_sequence BIGINT;
    END IF;
END $$;