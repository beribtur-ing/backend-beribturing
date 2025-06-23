-- Add missing columns to ITEM_CONDITION_CHECK table
ALTER TABLE item_condition_check
    ADD COLUMN photo_sequence BIGINT NOT NULL DEFAULT 0;