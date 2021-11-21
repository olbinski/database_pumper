alter system flush buffer_cache;
alter system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout OFF;

CREATE TABLE INVOICES_ARCHIVE AS (
    SELECT *
    FROM INVOICES i
             LEFT JOIN COMPANY c ON
        i.CLIENT_ID = c.COMPANY_ID
    WHERE c.NAME LIKE 'Effertz-Reynolds'
      AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss')
);

CREATE TABLE INVOICE_ITEMS_ARCHIVE AS (
    SELECT *
    FROM INVOICE_ITEMS ii
    WHERE ii.INVOICE_ID IN (
        SELECT INVOICE_ID
        FROM INVOICES i
                 LEFT JOIN COMPANY c ON
            i.CLIENT_ID = c.COMPANY_ID
        WHERE c.NAME LIKE 'Effertz-Reynolds'
          AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss')
    )
);


DELETE
FROM INVOICE_ITEMS ii
WHERE ii.INVOICE_ID IN (
    SELECT INVOICE_ID
    FROM INVOICES i
             LEFT JOIN COMPANY c ON
        i.CLIENT_ID = c.COMPANY_ID
    WHERE c.NAME LIKE 'Effertz-Reynolds'
      AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss')
);



DELETE
FROM INVOICES i
WHERE i.client_id in (
    select COMPANY_ID
    from company c
    where c.NAME LIKE 'Effertz-Reynolds'
)
  AND i.INVOICE_DATE < to_timestamp('01-01-2020 00:00:00', 'dd-mm-yyyy hh24:mi:ss');


INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 4, (dbms_utility.get_time - :n) / 100);

alter
    system flush buffer_cache;
alter
    system flush shared_pool;
