alter table item_condition_photo
    alter column url type varchar(500);

alter table lendee
    alter column avatar_url type varchar(500);

alter table lender
    alter column avatar_url type varchar(500);

alter table maintenance_photo
    alter column url type varchar(500);

alter table product_category
    alter column icon_url type varchar(500);

alter table product_image
    alter column url type varchar(500);