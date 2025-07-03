-- Add security settings column to lendee table
-- This allows storing security preferences for renters as JSON

ALTER TABLE LENDEE ADD COLUMN security_settings TEXT;