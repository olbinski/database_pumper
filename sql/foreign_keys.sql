ALTER TABLE STORES
    ADD CONSTRAINT FK_STORES_ADDRESSES FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES (ADDRESS_ID);
ALTER TABLE STORES
    ADD CONSTRAINT FK_STORES_COMPANY FOREIGN KEY (OWNER_COMPANY_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE STORES_PRODUCTS
    ADD CONSTRAINT FK_STORES_PRODUCTS_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID);
ALTER TABLE STORES_PRODUCTS
    ADD CONSTRAINT FK_STORES_PRODUCTS_STORES FOREIGN KEY (STORE_ID) REFERENCES STORES (STORE_ID);
ALTER TABLE PRICE_LIST
    ADD CONSTRAINT FK_PRICE_LIST_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID);
ALTER TABLE INVOICES
    ADD CONSTRAINT FK_INVOICES_STORES FOREIGN KEY (SUPPLIER_ID) REFERENCES STORES (STORE_ID);
ALTER TABLE INVOICES
    ADD CONSTRAINT FK_INVOICES_COMPANY FOREIGN KEY (CLIENT_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE INVOICE_ITEMS
    ADD CONSTRAINT FK_INVOICE_ITEMS_PRODUCT FOREIGN KEY (ITEM_ID) REFERENCES PRODUCT (PRODUCT_ID);
ALTER TABLE PRODUCT
    ADD CONSTRAINT FK_PRODUCT_CATEGORIES FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES (CATEGORY_ID);
ALTER TABLE COMPANY
    ADD CONSTRAINT FK_COMPANY_ADDRESSES FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES (ADDRESS_ID);
ALTER TABLE INVOICE_ITEMS
    ADD CONSTRAINT FK_INVOICE_ITEMS_INVOICES FOREIGN KEY (INVOICE_ID) REFERENCES INVOICES (INVOICE_ID);
ALTER TABLE CATEGORIES
    ADD CONSTRAINT FK_CATEGORIES_CATEGORIES FOREIGN KEY (PARENT_CATEGORY_ID) REFERENCES CATEGORIES (CATEGORY_ID);