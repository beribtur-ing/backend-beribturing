-- Add privacy settings column to lendee table
-- This allows storing privacy preferences for renters as JSON

ALTER TABLE LENDEE ADD COLUMN privacy_settings TEXT;