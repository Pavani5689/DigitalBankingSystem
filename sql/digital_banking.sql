-- Digital Banking & Transaction Management System
-- Run:  mysql -u root -p < digital_banking.sql      (password: Nithin123)

CREATE DATABASE IF NOT EXISTS digital_banking;
USE digital_banking;

CREATE TABLE IF NOT EXISTS customer (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100)  NOT NULL,
    email          VARCHAR(100)  NOT NULL UNIQUE,
    password       VARCHAR(100)  NOT NULL,
    age            INT           NOT NULL,
    gender         VARCHAR(10),
    mobile         VARCHAR(15),
    address        VARCHAR(255),
    account_number BIGINT        UNIQUE,
    balance        DECIMAL(15,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE IF NOT EXISTS transactions (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT        NOT NULL,
    type           VARCHAR(20)   NOT NULL,   -- DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT
    amount         DECIMAL(15,2) NOT NULL,
    balance_after  DECIMAL(15,2) NOT NULL,
    description    VARCHAR(255),
    txn_date       TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_txn_account FOREIGN KEY (account_number)
        REFERENCES customer(account_number) ON DELETE CASCADE
);
