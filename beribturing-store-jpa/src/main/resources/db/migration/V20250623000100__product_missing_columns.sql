-- Add missing columns to PRODUCT table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'product' AND column_name = 'variant_sequence') THEN
        ALTER TABLE product ADD COLUMN variant_sequence BIGINT;
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'product' AND column_name = 'active') THEN
        ALTER TABLE product ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;
    END IF;
END $$;