hive -f  /home/shashi/second_time 
hive -f  /home/shashi/drop_table 
hive -f  /home/shashi/create_tweets_avro_table.hql

echo "select   id , \"|\" ,count(text)    from  tweets_avro_table  where  id=$1 GROUP BY  id  "  > /home/shashi/query2.hql

hive  -f   /home/shashi/query2.hql  >  /home/shashi/output2

