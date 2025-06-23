-- Add missing columns to PRODUCT_IMAGE table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'product_image' AND column_name = 'active') THEN
        ALTER TABLE product_image ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;
    END IF;
END $$;