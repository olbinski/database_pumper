alter system flush buffer_cache;
alter system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout OFF;

WITH categories_rec (CATEGORY_ID,
                     NAME,
                     PARENT_CATEGORY_ID) AS (
    SELECT CATEGORY_ID,
           NAME,
           PARENT_CATEGORY_ID
    FROM CATEGORIES
    WHERE PARENT_CATEGORY_ID IS NULL
      AND NAME = 'Machinery'
    UNION ALL
    SELECT e.CATEGORY_ID,
           e.NAME,
           e.PARENT_CATEGORY_ID
    FROM CATEGORIES e
             INNER JOIN categories_rec o
                        ON o.CATEGORY_ID = e.PARENT_CATEGORY_ID
)
SELECT SUM((1 - DISCOUNT) * NET_PRICE * QUANTITY * VAT)
FROM (
         (SELECT INVOICE_ID
          FROM INVOICES
          WHERE INVOICE_DATE < DATE '2016-01-01') i INNER JOIN INVOICE_ITEMS ii ON i.INVOICE_ID = ii.INVOICE_ID
         )
         INNER JOIN
     (SELECT PRODUCT_ID
      FROM PRODUCTS p
      WHERE p.CATEGORY_ID IN (SELECT CATEGORY_ID FROM categories_rec)) prod ON ii.ITEM_ID = prod.PRODUCT_ID;

INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 3, (dbms_utility.get_time - :n) / 100);
alter system flush buffer_cache;
alter system flush shared_pool;
