alter system flush buffer_cache;
alter system flush shared_pool; 
variable n number
exec :n := dbms_utility.get_time;  

INSERT
	INTO
	invoices (INVOICE_ID,	INVOICE_DATE,	supply_date,	SUPPLIER_ID,	CLIENT_ID)
VALUES (
INVOICE_SEQ.nextval,to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss'),to_timestamp('18-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss'),15,1000
);

INSERT	INTO
	INVOCIE_ITEMS (INVOICE_ITEM_ID,	INVOICE_iD,	ITEM_ID,	NET_PRICE,	VAT,	QUANTITY,	DISCOUNT )
VALUES (INVOICE_ITEMS_SEQ.nextval,
INVOICE_SEQ.currval,
15, 
(
SELECT	NET_PRICE FROM PRICE_LIST pl
WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT
		max(pl2.EFFECTIVE_FROM)
	FROM
		PRICE_LIST pl2
	WHERE
		pl2.PRODUCT_ID = 15
		AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
)),
(
SELECT	 VAT FROM	PRICE_LIST pl
WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT
		max(pl2.EFFECTIVE_FROM)
	FROM
		PRICE_LIST pl2
	WHERE
		pl2.PRODUCT_ID = 15
		AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
)),
1 ,
0);

INSERT	INTO	INVOCIE_ITEMS (INVOICE_ITEM_ID,	INVOICE_iD,	ITEM_ID,	NET_PRICE,	VAT,	QUANTITY,	DISCOUNT )
VALUES (INVOICE_ITEMS_SEQ.nextval, INVOICE_SEQ.currval, 88, 
(SELECT NET_PRICE FROM PRICE_LIST pl
WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT
		max(pl2.EFFECTIVE_FROM)
	FROM
		PRICE_LIST pl2
	WHERE
		pl2.PRODUCT_ID = 88	AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
)),
(
SELECT	 VAT FROM	PRICE_LIST pl
WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT
		max(pl2.EFFECTIVE_FROM)
	FROM
		PRICE_LIST pl2
	WHERE
		pl2.PRODUCT_ID = 88 AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
)),1 ,0);

INSERT	INTO	INVOCIE_ITEMS (INVOICE_ITEM_ID,	INVOICE_iD,	ITEM_ID,	NET_PRICE,	VAT,	QUANTITY,	DISCOUNT )
VALUES (INVOICE_ITEMS_SEQ.nextval, INVOICE_SEQ.currval, 999, 
(
SELECT	NET_PRICE
FROM
	PRICE_LIST pl
WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT
		max(pl2.EFFECTIVE_FROM)
	FROM
		PRICE_LIST pl2
	WHERE
		pl2.PRODUCT_ID = 999
		AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
)),
(
SELECT	 VAT FROM	 PRICE_LIST pl WHERE
	pl.EFFECTIVE_FROM IN (
	SELECT max(pl2.EFFECTIVE_FROM)
    FROM PRICE_LIST pl2
    WHERE pl2.PRODUCT_ID = 999
      AND EFFECTIVE_FROM < to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
    )), 1, 0);

INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 5, (dbms_utility.get_time - :n) / 100);

-- TRUNCATE TABLE INVOICES;
-- TRUNCATE TABLE INVOCIE_ITEMS;
--
-- INSERT INTO INVOICES (select * from X_BACKUP_INVOICES);
-- INSERT INTO INVOCIE_ITEMS (select * from X_BACKUP_INVOCIE_ITEMS);

alter
system flush buffer_cache;
alter
system flush shared_pool;
