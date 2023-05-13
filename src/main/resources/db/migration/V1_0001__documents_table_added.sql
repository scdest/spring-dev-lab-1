-- V1_0001__documents_table_added.sql

CREATE TABLE documents (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    signed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    owner_id INTEGER NOT NULL
);