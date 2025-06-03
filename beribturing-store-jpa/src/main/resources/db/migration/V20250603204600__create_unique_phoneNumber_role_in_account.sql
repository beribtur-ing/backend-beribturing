ALTER TABLE account
    DROP CONSTRAINT uc_account_phonenumber;

ALTER TABLE ACCOUNT
    ADD CONSTRAINT uq_account_phone_role UNIQUE (phone_number, role);
