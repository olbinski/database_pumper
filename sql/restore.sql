ALTER TABLE STORES_PRODUCTS
    DROP CONSTRAINT FK_STORES_PRODUCTS_PRODUCT;
ALTER TABLE PRICE_LIST
    DROP CONSTRAINT FK_PRICE_LIST_PRODUCT;

drop TABLE INVOICE_ITEMS;
drop table INVOICES;
drop table PRODUCTS;


create table INVOICES as (Select *
                          from X_BACKUP_INVOICES xbi);
create table INVOICE_ITEMS as (Select *
                               from X_BACKUP_INVOICE_ITEM xbii);
create table PRODUCTS as (Select *
                          from X_BACKUP_PORDUCT xbp);

ALTER TABLE INVOICES
    ADD CONSTRAINT INVOICES_PK PRIMARY KEY (INVOICE_ID) ENABLE;
ALTER TABLE INVOICE_ITEMS
    ADD CONSTRAINT INVOICE_ITEMS_PK PRIMARY KEY (INVOICE_ITEM_ID) ENABLE;
ALTER TABLE PRODUCTS
    ADD CONSTRAINT PRODUCTS_PK PRIMARY KEY (PRODUCT_ID) ENABLE;


ALTER TABLE INVOICES
    ADD CONSTRAINT FK_INVOICES_STORES FOREIGN KEY (SUPPLIER_ID) REFERENCES STORES (STORE_ID);
ALTER TABLE INVOICES
    ADD CONSTRAINT FK_INVOICES_COMPANY FOREIGN KEY (CLIENT_ID) REFERENCES COMPANY (COMPANY_ID);

ALTER TABLE INVOICE_ITEMS
    ADD CONSTRAINT FK_INVOICE_ITEMS_PRODUCT FOREIGN KEY (ITEM_ID) REFERENCES PRODUCTS (PRODUCT_ID);

ALTER TABLE INVOICE_ITEMS
    ADD CONSTRAINT FK_INVOICE_ITEMS_INVOICES FOREIGN KEY (INVOICE_ID) REFERENCES INVOICES (INVOICE_ID);


ALTER TABLE STORES_PRODUCTS
    ADD CONSTRAINT FK_STORES_PRODUCTS_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID);
ALTER TABLE PRICE_LIST
    ADD CONSTRAINT FK_PRICE_LIST_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID);