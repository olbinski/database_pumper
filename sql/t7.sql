alter system flush buffer_cache;
alter system flush shared_pool; 
variable n number
exec :n := dbms_utility.get_time;  
  
INSERT INTO price_list (effective_from,product_id,price_list_id,net_price,vat) 

select SYSTIMESTAMP, PRODUCT_ID, PRICE_LIST_SEQ.nextval, (net_price*1.1),vat From ( 

select MAX(pl.effective_from), pl.product_id ,MAX(pl.price_list_id) price_list_id , MAX(pl.net_price) net_price, MAX(pl.vat) vat from (Select PRODUCT_ID from  PRODUCT p  

where p.CATEGORY_ID in (  

  

WITH categories_rec (CATEGORY_ID ,   

        NAME ,  

        PARENT_CATEGORY_ID ) AS (  

    SELECT         

        CATEGORY_ID ,   

        NAME ,  

        PARENT_CATEGORY_ID           

    FROM         

        ROOT.CATEGORIES  

    WHERE PARENT_CATEGORY_ID IS NULL AND NAME = 'Renewables Environment' 

    UNION ALL  

    SELECT   

        e.CATEGORY_ID,   

        e.NAME,  

        e.PARENT_CATEGORY_ID  

    FROM   

        ROOT.CATEGORIES e  

        INNER JOIN categories_rec o   

            ON o.CATEGORY_ID = e.PARENT_CATEGORY_ID   

    )  

    select CATEGORY_ID from categories_rec  

)) pp INNER JOIN PRICE_LIST pl ON pl.product_id = pp.product_id 

group by pl.product_id); 

INSERT INTO Timers(timer_id,querry_id,total_time) VALUES(TIMER_SEQ.nextval,7, (dbms_utility.get_time-:n)/100);

TRUNCATE TABLE price_list;
INSERT INTO price_list (select * from X_BACKUP_price_list);
alter system flush buffer_cache;
alter system flush shared_pool;