INSERT INTO public.networks (id, created_at, updated_at) VALUES ('5fa6fe13-83b0-4ea7-95d3-2b05194051a8', '2024-03-03 09:28:15.318432', '2024-03-03 09:28:15.318432');
INSERT INTO public.identities (id, schema_id, traits, created_at, updated_at, nid, state, state_changed_at, metadata_public, metadata_admin) VALUES ('f6a401de-8408-45e0-b083-49eb9723b573', 'default', '{"email": "a@a.com"}', '2024-03-03 09:33:28.235995', '2024-03-03 09:33:28.235995', '5fa6fe13-83b0-4ea7-95d3-2b05194051a8', 'active', '2024-03-03 09:33:28.234788', null, null);
INSERT INTO public.identity_credentials (id, config, identity_credential_type_id, identity_id, created_at, updated_at, nid, version) VALUES ('a2348d3b-5e41-4b6a-abd5-e86bbf412468', '{"hashed_password": "$2a$08$x36I6DWbg8Vlq3dOd2xNLe7VIvRX/rNM2AnKvpQu9hbf69hMwpbVO"}', '78c1b41d-8341-4507-aa60-aff1d4369670', 'f6a401de-8408-45e0-b083-49eb9723b573', '2024-03-03 09:33:28.259005', '2024-03-03 09:33:28.259005', '5fa6fe13-83b0-4ea7-95d3-2b05194051a8', 0);
INSERT INTO public.identity_credential_identifiers (id, identifier, identity_credential_id, created_at, updated_at, nid, identity_credential_type_id) VALUES ('df9e5acd-72fd-4e05-bfed-9a99c21d211c', 'a@a.com', 'a2348d3b-5e41-4b6a-abd5-e86bbf412468', '2024-03-03 09:33:28.264610', '2024-03-03 09:33:28.264610', '5fa6fe13-83b0-4ea7-95d3-2b05194051a8', '78c1b41d-8341-4507-aa60-aff1d4369670');
INSERT INTO public.identity_recovery_addresses (id, via, value, identity_id, created_at, updated_at, nid) VALUES ('841a978d-72e0-4df8-b878-84a1d175ea23', 'email', 'a@a.com', 'f6a401de-8408-45e0-b083-49eb9723b573', '2024-03-03 09:33:28.251094', '2024-03-03 09:33:28.251094', '5fa6fe13-83b0-4ea7-95d3-2b05194051a8');
INSERT INTO public.identity_verifiable_addresses (id, status, via, verified, value, verified_at, identity_id, created_at, updated_at, nid) VALUES ('747eb663-f821-423d-b489-c5314a8a2fe2', 'completed', 'email', true, 'a@a.com', '2024-03-03 09:33:43.305774', 'f6a401de-8408-45e0-b083-49eb9723b573', '2024-03-03 09:33:28.243044', '2024-03-03 09:33:28.243044', '5fa6fe13-83b0-4ea7-95d3-2b05194051a8');
