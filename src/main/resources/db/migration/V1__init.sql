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
    rental_fee numeric(10,2),
    district varchar(255),
    estate_name varchar(255),
    floor numeric,
    total_floors numeric,
    doorbell_code varchar(20),
    is_elevator_in_building bool,
    development_type varchar(50),
    windows_type varchar(50),
-- GARAGE
    garage_number varchar(50),
    garage_level varchar(50),
    garage_entrance_type varchar(50),
    garage_entrance_code varchar(50),
    garage_type varchar(50)
);

CREATE TABLE IF NOT EXISTS unit_equipment
(
    id            UUID PRIMARY KEY,
    unit_id UUID NOT NULL,
    type         varchar(50)
);

CREATE TABLE IF NOT EXISTS associated_room
(
    id            UUID PRIMARY KEY,
    unit_id UUID NOT NULL,
    type         varchar(50)
);

CREATE TABLE IF NOT EXISTS utility
(
    id            UUID PRIMARY KEY,
    unit_id UUID NOT NULL,
    description        varchar(255),
    type         varchar(50)
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
    current_step_type VARCHAR(50),
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


INSERT INTO rentlink.unit (id, account_id, name, unit_type, rental_type, heating_type, surface, rooms_no, city, postal_code, street, building_no, apartment_no, country, additional_information, insurance_number, insurance_company, insurance_due_date, cooperative_fee, rental_fee, district, estate_name, floor, total_floors, doorbell_code, is_elevator_in_building, development_type, windows_type) VALUES ('a42e3bd2-d31b-4b25-aba1-246bdec856ce', 'f6a401de-8408-45e0-b083-49eb9723b573', 'Testowa cale miejsce', 'APARTMENT', 'ENTIRE_PLACE', 'URBAN', 0, 0, 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2024-03-27', 0.00, 0.00, 'string', 'string', 0, 0, 'string', true, 'BLOCK', 'PLASTIC');
INSERT INTO rentlink.unit (id, account_id, name, unit_type, rental_type, heating_type, surface, rooms_no, city, postal_code, street, building_no, apartment_no, country, additional_information, insurance_number, insurance_company, insurance_due_date, cooperative_fee, rental_fee, district, estate_name, floor, total_floors, doorbell_code, is_elevator_in_building, development_type, windows_type) VALUES ('a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'f6a401de-8408-45e0-b083-49eb9723b573', 'Testowa pokoje', 'APARTMENT', 'ROOMS', 'URBAN', 0, 0, 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2024-03-27', 0.00, 0.00, 'string', 'string', 0, 0, 'string', true, 'BLOCK', 'PLASTIC');

INSERT INTO rentlink.associated_room (id, unit_id, type) VALUES ('c9ebb3cb-184a-4dfe-80f3-f7a6dde58a71', 'a42e3bd2-d31b-4b25-aba1-246bdec856ce', 'HALLWAY');
INSERT INTO rentlink.unit_equipment (id, unit_id, type) VALUES ('328adfff-461b-4b69-8e6f-9b8045d1c955', 'a42e3bd2-d31b-4b25-aba1-246bdec856ce', 'ROOM_FURNITURE');
INSERT INTO rentlink.utility (id, unit_id, type) VALUES ('aab4502c-d278-4666-a2fb-b37a140daadf', 'a42e3bd2-d31b-4b25-aba1-246bdec856ce', 'COLD_WATER');

INSERT INTO rentlink.associated_room (id, unit_id, type) VALUES ('c9ebb3cb-184a-4dfe-80f3-f7a6dde58a72', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'HALLWAY');
INSERT INTO rentlink.unit_equipment (id, unit_id, type) VALUES ('328adfff-461b-4b69-8e6f-9b8045d1c956', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'ROOM_FURNITURE');
INSERT INTO rentlink.utility (id, unit_id, description, type) VALUES ('aab4502c-d278-4666-a2fb-b37a140daade', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', '500 MB/S', 'INTERNET');


INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('a42e3bd2-d31b-4b25-aba1-246bdec856ce','f6a401de-8408-45e0-b083-49eb9723b573', 'a42e3bd2-d31b-4b25-aba1-246bdec856ce', 'Całe miejsce');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('0b6a8d06-a1e8-4b34-a9d5-8c2dfa70eac5','f6a401de-8408-45e0-b083-49eb9723b573', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'Pokój nr 1');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('3ea6e5a2-1621-41f1-b3c0-9bf4b661e629','f6a401de-8408-45e0-b083-49eb9723b573', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'Pokój nr 2');
INSERT INTO rental_option (id, account_id, unit_id, name) VALUES ('184ba51f-af69-407a-aa3e-6dd1748abc74','f6a401de-8408-45e0-b083-49eb9723b573', 'a42e3bd2-d31b-4b25-aba1-246bdec856c1', 'Pokój nr 3');
