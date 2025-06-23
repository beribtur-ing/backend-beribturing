-- Add missing columns to RESERVATION table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'reservation' AND column_name = 'owner_id') THEN
        ALTER TABLE reservation ADD COLUMN owner_id VARCHAR(255) NOT NULL DEFAULT '';
    END IF;
END $$;