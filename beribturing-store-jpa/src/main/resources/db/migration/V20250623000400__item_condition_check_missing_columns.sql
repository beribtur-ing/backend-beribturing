-- Add missing columns to ITEM_CONDITION_CHECK table
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'item_condition_check' AND column_name = 'photo_sequence') THEN
        ALTER TABLE item_condition_check ADD COLUMN photo_sequence BIGINT NOT NULL DEFAULT 0;
    END IF;
END $$;