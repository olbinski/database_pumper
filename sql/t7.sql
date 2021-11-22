alter
    system flush buffer_cache;
alter
    system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout ON;

INSERT INTO price_list (effective_from, product_id, price_list_id, net_price, vat)
SELECT SYSTIMESTAMP, p.PRODUCT_ID, PRICE_LIST_SEQ.nextval, (net_price * 1.1), vat
FROM PRICE_LIST pl
         JOIN
     PRODUCTS p ON pl.PRODUCT_ID = p.PRODUCT_ID
WHERE p.COLOR in ('WHITE', 'BLACK', 'GRAY')
  AND p.MATERIAL IN ('MARBLE', 'WOOL', 'SILK', 'WOODEN', 'PAPER', 'RUBBER', 'STEEL')
  AND p.CATEGORY_ID in (
    WITH categories_rec (CATEGORY_ID, NAME, PARENT_CATEGORY_ID) AS (
        SELECT CATEGORY_ID, NAME, PARENT_CATEGORY_ID
        FROM CATEGORIES
        WHERE PARENT_CATEGORY_ID IS NULL
          AND NAME = 'Government Administration'

        UNION ALL

        SELECT e.CATEGORY_ID,
               e.NAME,
               e.PARENT_CATEGORY_ID
        FROM CATEGORIES e
                 INNER JOIN categories_rec o
                            ON o.CATEGORY_ID = e.PARENT_CATEGORY_ID
    )
    select CATEGORY_ID
    from categories_rec
)
  AND pl.EFFECTIVE_FROM = (SELECT max(pl2.EFFECTIVE_FROM)
                           FROM PRICE_LIST pl2
                           WHERE pl2.PRODUCT_ID = p.PRODUCT_ID
                             AND pl2.EFFECTIVE_FROM <= to_timestamp('08-11-2021 17:15:00', 'dd-mm-yyyy hh24:mi:ss')
);



INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 7, (dbms_utility.get_time - :n) / 100);

alter
    system flush buffer_cache;
alter
    system flush shared_pool;
