CREATE TABLE X_BACKUP_INVOICE_ITEM AS (SELECT *
                                       FROM INVOICE_ITEMS ii);
CREATE TABLE X_BACKUP_INVOICES AS (SELECT *
                                   FROM INVOICES i);
CREATE TABLE X_BACKUP_ADDRESSES AS (SELECT *
                                    FROM ADDRESSES a);
CREATE TABLE X_BACKUP_CATEGORIES AS (SELECT *
                                     FROM CATEGORIES c);
CREATE TABLE X_BACKUP_COMPANY AS (SELECT *
                                  FROM COMPANY c);
CREATE TABLE X_BACKUP_PRICE_LIST AS (SELECT *
                                     FROM PRICE_LIST pl);
CREATE TABLE X_BACKUP_PRODUCT AS (SELECT *
                                  FROM PRODUCTS p);
CREATE TABLE X_BACKUP_STORES AS (SELECT *
                                 FROM STORES s);
CREATE TABLE X_BACKUP_STORES_PRODUCT AS (SELECT *
                                         FROM STORES_PRODUCTS sp);
