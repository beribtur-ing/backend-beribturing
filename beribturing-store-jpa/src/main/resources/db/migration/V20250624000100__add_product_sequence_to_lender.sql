-- Add product_sequence column to LENDER table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'lender' AND column_name = 'product_sequence') THEN
        ALTER TABLE lender ADD COLUMN product_sequence BIGINT DEFAULT 0;
    END IF;
END $$;