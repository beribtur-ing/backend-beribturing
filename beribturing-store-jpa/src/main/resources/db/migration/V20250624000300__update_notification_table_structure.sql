-- Update NOTIFICATION table structure to match new entity model

-- Drop existing notification table to recreate with new structure
DROP TABLE IF EXISTS notification;

-- Create new NOTIFICATION table with updated structure
CREATE TABLE notification
(
    id             VARCHAR(255)                              NOT NULL,
    entity_version BIGINT                                    NOT NULL,
    registered_by  VARCHAR(255),
    modified_by    VARCHAR(255),
    registered_on  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    modified_on    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    receiver_id    VARCHAR(255)                              NOT NULL,
    sender_id      VARCHAR(255)                              NOT NULL,
    message        TEXT                                      NOT NULL,
    type           VARCHAR(255)                              NOT NULL,
    channel_type   VARCHAR(255)                              NOT NULL,
    status         VARCHAR(255)                              NOT NULL,
    sent_at        TIMESTAMP WITHOUT TIME ZONE,
    received_at    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

-- Create indexes for better query performance
CREATE INDEX idx_notification_receiver_id ON notification (receiver_id);
CREATE INDEX idx_notification_sender_id ON notification (sender_id);
CREATE INDEX idx_notification_status ON notification (status);
CREATE INDEX idx_notification_channel_type ON notification (channel_type);
CREATE INDEX idx_notification_type ON notification (type);
CREATE INDEX idx_notification_receiver_status ON notification (receiver_id, status);