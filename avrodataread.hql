CREATE TABLE tweets
  ROW FORMAT SERDE
     'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
  STORED AS INPUTFORMAT
     'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
  OUTPUTFORMAT
     'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
  TBLPROPERTIES ('avro.schema.url'='file:///home/shashi/TwitterDataAvroSchema.avsc') ;

LOAD DATA INPATH '/user/Hadoop/twitter_data/FlumeData.*' OVERWRITE INTO TABLE tweets;

