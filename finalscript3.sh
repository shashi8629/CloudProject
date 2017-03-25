hive -f  /home/shashi/second_time 
hive -f  /home/shashi/drop_table 
hive -f  /home/shashi/create_tweets_avro_table.hql
#(842821443532537857i,842821724559360006)"

echo "select  id, \"|\" ,retweet_count   from  tweets_avro_table  where  id  in  ($1,$2)" > /home/shashi/query3.hql 
hive -f  /home/shashi/query3.hql  >  output3
