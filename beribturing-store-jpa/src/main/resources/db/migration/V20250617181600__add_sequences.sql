ALTER TABLE product
    ADD COLUMN variant_sequence BIGINT NOT NULL DEFAULT 0;

ALTER TABLE product_variant
    ADD COLUMN image_sequence BIGINT NOT NULL DEFAULT 0;

ALTER TABLE item_condition_check
    ADD COLUMN photo_sequence BIGINT NOT NULL DEFAULT 0;