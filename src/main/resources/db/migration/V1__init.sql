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
    identity_document_issue_date date,
    identity_document_due_date date,
    tax_account_number       VARCHAR(50),
    bank_account_number      VARCHAR(50),
    bank_name      VARCHAR(50),
    company_name       VARCHAR(255),
    company_city VARCHAR(255),
    company_postal_code VARCHAR(255),
    company_street VARCHAR(255),
    company_building_no VARCHAR(255),
    company_apartment_no VARCHAR(255),
    company_country VARCHAR(255),
    social_number VARCHAR(255),
    nip                      VARCHAR(15),
    krs                      VARCHAR(15),
    regon                    VARCHAR(15),
    phone_number         varchar(20),
    email         varchar(255)
);

CREATE TABLE IF NOT EXISTS unit_owner_emergency_contact
(
    id            UUID PRIMARY KEY,
    unit_owner_id UUID NOT NULL,
    number         varchar(20),
    name          varchar(255)
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
    identity_document_issue_date date,
    identity_document_due_date date,
    bank_account_number      VARCHAR(50),
    bank_name      VARCHAR(50),
    company_name       VARCHAR(255),
    company_city VARCHAR(255),
    company_postal_code VARCHAR(255),
    company_street VARCHAR(255),
    company_building_no VARCHAR(255),
    company_apartment_no VARCHAR(255),
    company_country VARCHAR(255),
    social_number VARCHAR(255),
    nip                      VARCHAR(15),
    krs                      VARCHAR(15),
    regon                    VARCHAR(15),
    phone_number         varchar(20),
    email         varchar(255)
);



CREATE TABLE IF NOT EXISTS tenant_emergency_contact
(
    id            UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    number         varchar(20),
    name          varchar(255)
);


CREATE TABLE IF NOT EXISTS unit
(
    id UUID PRIMARY KEY,
    name varchar(255),
    unit_type varchar(20),
    rental_type varchar(20),
    heating_type varchar(20),
    rental_option_type varchar(20),
    surface numeric,
    rooms_no numeric,
    city varchar(50),
    postal_code varchar(10),
    street varchar(255),
    building_no varchar(20),
    apartment_no varchar(20),
    country varchar(50),
    additional_information varchar(1000),
    insurance_number varchar(20),
    insurance_company varchar(50),
    insurance_due_date date,
    cooperative_fee numeric(10,2),
    rental_fee numeric(10,2)
);

CREATE TABLE IF NOT EXISTS rental_option
(
    id UUID PRIMARY KEY,
    unit_id UUID,
    name varchar(255)
);


CREATE TABLE IF NOT EXISTS notification
(
    id UUID PRIMARY KEY,
    title varchar(255),
    description varchar(255),
    priority varchar(20),
    received bool
);

CREATE TABLE IF NOT EXISTS rental_process
(
    id UUID PRIMARY KEY,
    rental_option_id UUID,
    name VARCHAR(255),
    definition_type VARCHAR(20),
    steps JSONB,
    current_step_id UUID
)