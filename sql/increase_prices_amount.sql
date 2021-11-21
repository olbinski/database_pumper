INSERT
INTO PRICE_LIST (PRICE_LIST_ID,
                 NET_PRICE,
                 EFFECTIVE_FROM,
                 VAT,
                 PRODUCT_ID)
SELECT PRICE_LIST_SEQ.nextval,
       price,
       date1,
       VAT,
       PRODUCT_ID
FROM (
         SELECT min(pl.NET_PRICE) + dbms_random.value() * (max(pl.NET_PRICE) - min(pl.NET_PRICE)) AS price,
                TO_DATE('01/01/2015 00:00:01', 'DD/MM/YYYY HH24:MI:SS') + dbms_random.value(0,
                                                                                            TO_DATE('01/01/2015 00:00:01', 'DD/MM/YYYY HH24:MI:SS') -
                                                                                            TO_DATE('01/10/2021 00:00:01', 'DD/MM/YYYY HH24:MI:SS') +
                                                                                            1)    AS date1,
                pl.VAT,
                pl.PRODUCT_ID
         FROM PRICE_LIST pl
         GROUP BY PL.PRODUCT_ID,
                  pl.VAT
     );
