-- Add appearance settings column to lendee table
-- This allows storing appearance preferences for renters as JSON

ALTER TABLE LENDEE ADD COLUMN appearance_settings TEXT;