hive -f  /home/shashi/second_time 
hive -f  /home/shashi/drop_table
hive -f  /home/shashi/create_tweets_avro_table.hql
echo "select  id ,"'"|"'" ,text  from  tweets_avro_table  where created_at= \"$1\" "> query1.hql
hive  -f  query1.hql  > output1

