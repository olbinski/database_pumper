alter system flush buffer_cache;
alter system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout OFF;

SELECT p.NAME,
       p2.NAME,
       count1 AS times_int_the_same_invoice
FROM (
         SELECT ii.ITEM_ID  AS FIRST_ITEM,
                ii2.ITEM_ID AS SECOND_ITEM,
                count(*)    AS COUNT1
         FROM INVOICE_ITEMS ii
                  INNER JOIN INVOICE_ITEMS ii2 ON
                     ii.INVOICE_ID = ii2.INVOICE_ID
                 AND ii.ITEM_ID <> ii2.ITEM_ID
         WHERE ii.ITEM_ID IN (
             SELECT ITEM_ID
             FROM (
                      SELECT ii.ITEM_ID,
                             count(*),
                             ROW_NUMBER() OVER (ORDER BY count(*) DESC NULLS LAST) rnm
                      FROM INVOICE_ITEMS ii
                      GROUP BY ii.ITEM_ID
                  )
             WHERE rnm <= 5
         )
         GROUP BY ii.ITEM_ID,
                  ii2.ITEM_ID
     ) x
         LEFT JOIN PRODUCTS p ON FIRST_ITEM = p.PRODUCT_ID
         LEFT JOIN PRODUCTS p2 ON SECOND_ITEM = p2.PRODUCT_ID
ORDER BY COUNT1 DESC;

INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 2, (dbms_utility.get_time - :n) / 100);
alter
    system flush buffer_cache;
alter
    system flush shared_pool;
