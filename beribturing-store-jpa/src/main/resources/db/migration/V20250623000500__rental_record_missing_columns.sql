-- Add missing columns to RENTAL_RECORD table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'rental_record' AND column_name = 'owner_id') THEN
        ALTER TABLE rental_record ADD COLUMN owner_id VARCHAR(255) NOT NULL DEFAULT '';
    END IF;
END $$;