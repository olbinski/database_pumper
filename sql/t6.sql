alter system flush buffer_cache;
alter system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout OFF;


update PRODUCTS p
set p.PUBLISHED = p.PUBLISHED * -1 + 1
where p.CATEGORY_ID in (
    WITH categories_rec (CATEGORY_ID,
                         NAME,
                         PARENT_CATEGORY_ID) AS (
        SELECT CATEGORY_ID,
               NAME,
               PARENT_CATEGORY_ID
        FROM CATEGORIES
        WHERE PARENT_CATEGORY_ID IS NULL
          AND NAME = 'Glass, Ceramics and Concrete'
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
);

INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 6, (dbms_utility.get_time - :n) / 100);

alter
    system flush buffer_cache;
alter
    system flush shared_pool;
