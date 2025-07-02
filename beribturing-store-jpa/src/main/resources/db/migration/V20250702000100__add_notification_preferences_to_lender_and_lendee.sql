-- Add notification_preferences column to LENDER table
ALTER TABLE lender ADD COLUMN notification_preferences TEXT;

-- Add notification_preferences column to LENDEE table  
ALTER TABLE lendee ADD COLUMN notification_preferences TEXT;
