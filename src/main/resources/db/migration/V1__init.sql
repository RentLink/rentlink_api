CREATE TABLE IF NOT EXISTS unit_owner
(
    id                       UUID PRIMARY KEY,
    account_id               UUID NOT NULL,
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
    account_id               UUID NOT NULL,
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
    account_id               UUID NOT NULL,
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
    account_id               UUID NOT NULL,
    unit_id UUID,
    name varchar(255)
);


CREATE TABLE IF NOT EXISTS notification
(
    id UUID PRIMARY KEY,
    account_id               UUID NOT NULL,
    title varchar(255),
    description varchar(255),
    priority varchar(20),
    created_at timestamp,
    received bool
);

CREATE TABLE IF NOT EXISTS rental_process
(
    id UUID PRIMARY KEY,
    account_id               UUID NOT NULL,
    rental_option_id UUID,
    status VARCHAR(50),
    definition JSONB,
    current_step_id UUID,
    updated_at timestamp
);


CREATE TABLE IF NOT EXISTS email_order_outbox
(
    id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    email varchar(255),
    subject varchar(255),
    message text,
    files JSONB,
    created_at timestamp,
    sent_at timestamp,
    status varchar(20),
    error_message text
);

CREATE TABLE IF NOT EXISTS awaiting_documents_task
(
    id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    rental_process_id UUID,
    email varchar(255),
    recognition_code UUID,
    status VARCHAR(50)
);


INSERT INTO unit (id, account_id, name, unit_type, rental_type, heating_type, rental_option_type, surface, rooms_no, city, postal_code, street, building_no, apartment_no, country, additional_information, insurance_number, insurance_company, insurance_due_date, cooperative_fee, rental_fee) VALUES ('132eb354-a7bf-4e11-b9f7-48ebe817e1f7','f6a401de-8408-45e0-b083-49eb9723b573', 'Testowa', 'APARTMENT', 'WHOLE', 'TO', null, 111, 3, 'Testowe', '01-259', 'Testowa', '12a', '12', 'Polska', 'Testowe dod informacje', 'Testowa', 'Testowa', '2024-03-29', 123.00, 231.00);
INSERT INTO unit (id, account_id, name, unit_type, rental_type, heating_type, rental_option_type, surface, rooms_no, city, postal_code, street, building_no, apartment_no, country, additional_information, insurance_number, insurance_company, insurance_due_date, cooperative_fee, rental_fee) VALUES ('5e5905e1-a79e-4c5c-b987-1268c0bb271b','f6a401de-8408-45e0-b083-49eb9723b573', 'Testowa Dzielona', 'APARTMENT', 'ROOMS', 'TO', null, 123, 3, 'Testowe', '01-259', 'Testowa', '12c', '14', 'Testowe', 'Test', 'Test', 'test', '2024-03-29', 13231.00, 123123.00);

INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('b536323e-93b4-4afe-8f39-d11d7d4447b7','f6a401de-8408-45e0-b083-49eb9723b573', '132eb354-a7bf-4e11-b9f7-48ebe817e1f7', 'Całe miejsce');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('0b6a8d06-a1e8-4b34-a9d5-8c2dfa70eac5','f6a401de-8408-45e0-b083-49eb9723b573', '5e5905e1-a79e-4c5c-b987-1268c0bb271b', 'Pokój nr 1');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('3ea6e5a2-1621-41f1-b3c0-9bf4b661e629','f6a401de-8408-45e0-b083-49eb9723b573', '5e5905e1-a79e-4c5c-b987-1268c0bb271b', 'Pokój nr 2');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('184ba51f-af69-407a-aa3e-6dd1748abc74','f6a401de-8408-45e0-b083-49eb9723b573', '5e5905e1-a79e-4c5c-b987-1268c0bb271b', 'Pokój nr 3');
