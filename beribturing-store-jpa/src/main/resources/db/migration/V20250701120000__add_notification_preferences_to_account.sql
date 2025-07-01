-- Add notification preferences column to account table
-- This allows storing role-specific notification settings as JSON

ALTER TABLE ACCOUNT ADD COLUMN notification_preferences TEXT;