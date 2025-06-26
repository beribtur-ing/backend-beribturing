-- Add maintenance_request_sequence column to RENTAL_RECORD table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'rental_record' AND column_name = 'maintenance_request_sequence') THEN
        ALTER TABLE rental_record ADD COLUMN maintenance_request_sequence BIGINT DEFAULT 0;
    END IF;
END $$;