alter
system flush buffer_cache;
alter
system flush shared_pool;
variable
n number
exec :n := dbms_utility.get_time;  

-- CREATE TABLE INVOICE_ITEMS_ARCHIVE AS (
-- SELECT
-- 	*
-- FROM
-- 	INVOCIE_ITEMS ii
-- WHERE
-- 	ii.INVOICE_ID IN (
-- 	SELECT
-- 		INVOICE_ID
-- 	FROM
-- 		INVOICES i
-- 	LEFT JOIN COMPANY c ON
-- 		i.CLIENT_ID = c.COMPANY_ID
-- 	WHERE
-- 		c.NAME LIKE 'Kutch-Kuhic'
-- 		AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss')
-- )
-- );

CREATE TABLE INVOICES_ARCHIVE AS (
    SELECT *
    FROM INVOICES i
             LEFT JOIN COMPANY c ON
        i.CLIENT_ID = c.COMPANY_ID
    WHERE c.NAME LIKE 'Kutch-Kuhic'
	AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss') 
);


DELETE FROM
	INVOCIE_ITEMS ii
WHERE
	ii.INVOICE_ID IN (
	SELECT
		INVOICE_ID
	FROM
		INVOICES i
	LEFT JOIN COMPANY c ON
		i.CLIENT_ID = c.COMPANY_ID
	WHERE
		c.NAME LIKE 'Kutch-Kuhic'
		AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss') 
);



DELETE
FROM INVOICES i
WHERE i.client_id in (
    select COMPANY_ID
    from company c
    where c.NAME LIKE 'Kutch-Kuhic'
)
  AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss');


INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 4, (dbms_utility.get_time - :n) / 100);

-- TRUNCATE TABLE INVOICES;
-- TRUNCATE TABLE INVOCIE_ITEMS;
--
-- INSERT INTO INVOICES (select * from X_BACKUP_INVOICES);
-- INSERT INTO INVOCIE_ITEMS (select * from X_BACKUP_INVOCIE_ITEMS);

-- DROP TABLE INVOICES_ARCHIVE;
-- DROP TABLE INVOICE_ITEMS_ARCHIVE;

alter
system flush buffer_cache;
alter
system flush shared_pool;
