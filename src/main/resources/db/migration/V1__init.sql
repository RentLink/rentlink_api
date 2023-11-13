CREATE TABLE IF NOT EXISTS unit_owner
(
    id                       UUID PRIMARY KEY,
    legal_personality        VARCHAR(20),
    name                     VARCHAR(50),
    surname                  VARCHAR(50),
    gender                   VARCHAR(6),
    citizenship              VARCHAR(30),
    city                     VARCHAR(50),
    street                   VARCHAR(50),
    postal_code              VARCHAR(10),
    building_no              VARCHAR(10),
    apartment_no             VARCHAR(10),
    country                  VARCHAR(50),
    identity_document_type   VARCHAR(20),
    identity_document_number VARCHAR(50),
    tax_account_number       VARCHAR(50),
    bank_account_number      VARCHAR(50),
    nip                      VARCHAR(15),
    krs                      VARCHAR(15),
    regon                    VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS unit_owner_contact_details
(
    id            UUID PRIMARY KEY,
    unit_owner_id UUID NOT NULL,
    phone_number         varchar(20),
    email         varchar(255),
    FOREIGN KEY (unit_owner_id) REFERENCES unit_owner (id)
);

CREATE TABLE IF NOT EXISTS unit_owner_emergency_contact
(
    id            UUID PRIMARY KEY,
    unit_owner_id UUID NOT NULL,
    phone_number         varchar(20),
    name          varchar(255),
    email         varchar(255),
    FOREIGN KEY (unit_owner_id) REFERENCES unit_owner (id)
);

CREATE TABLE IF NOT EXISTS tenant
(
    id                       UUID PRIMARY KEY,
    legal_personality        VARCHAR(20),
    name                     VARCHAR(50),
    surname                  VARCHAR(50),
    gender                   VARCHAR(6),
    citizenship              VARCHAR(30),
    city                     VARCHAR(50),
    street                   VARCHAR(50),
    postal_code              VARCHAR(10),
    building_no              VARCHAR(10),
    apartment_no             VARCHAR(10),
    country                  VARCHAR(50),
    identity_document_type   VARCHAR(20),
    identity_document_number VARCHAR(50),
    tax_account_number       VARCHAR(50),
    bank_account_number      VARCHAR(50),
    nip                      VARCHAR(15),
    krs                      VARCHAR(15),
    regon                    VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS tenant_contact_details
(
    id            UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    phone_number         varchar(20),
    email         varchar(255),
    FOREIGN KEY (tenant_id) REFERENCES tenant (id)
);

CREATE TABLE IF NOT EXISTS tenant_emergency_contact
(
    id            UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    phone_number         varchar(20),
    name          varchar(255),
    email         varchar(255),
    FOREIGN KEY (tenant_id) REFERENCES tenant (id)
);