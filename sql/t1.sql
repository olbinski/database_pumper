alter system flush buffer_cache;
alter system flush shared_pool;
variable n number
exec :n := dbms_utility.get_time;
set termout OFF;

SELECT AVG(stValue.STORE_VALUE), adr.VOIVODESHIP
FROM ADDRESSES adr
         INNER JOIN (SELECT st.ADDRESS_ID, sstorage.STORE_VALUE
                     FROM STORES st
                              INNER JOIN (SELECT sp.STORE_ID, pp.NET_PRICE * sp.AVAILABLE AS STORE_VALUE
                                          FROM STORES_PRODUCTS sp
                                                   INNER JOIN
                                               (SELECT p.PRODUCT_ID, pl.NET_PRICE
                                                FROM PRODUCTS p
                                                         INNER JOIN (SELECT MAX(ppl.effective_from),
                                                                            ppl.product_id,
                                                                            MAX(ppl.net_price) net_price
                                                                     FROM PRICE_LIST ppl
                                                                     group by ppl.product_id) pl
                                                                    ON p.PRODUCT_ID = pl.PRODUCT_ID) pp
                                               ON sp.PRODUCT_ID = pp.PRODUCT_ID
                                          WHERE sp.AVAILABLE > 0) sstorage ON sstorage.STORE_ID = st.STORE_ID) stValue
                    ON adr.ADDRESS_ID = stValue.ADDRESS_ID
GROUP BY adr.VOIVODESHIP;

INSERT INTO Timers(timer_id, querry_id, total_time)
VALUES (TIMER_SEQ.nextval, 1, (dbms_utility.get_time - :n) / 100);
alter system flush buffer_cache;
alter system flush shared_pool;
set termout ON
