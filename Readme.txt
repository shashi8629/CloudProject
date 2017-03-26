

		TWITTER DATA ANALYTICS IMPLEMENTATION ON GOOGLE CLOUD
 All analytical services are very expensive so future goal is to make it very fast and cost efficient. In this project, we will try to build a cost effective, cost efficient and fast web service solution utilizing Google Cloud Platform. 
The main idea of this project is to build a structure that will help to analytics on twitter data. Right Now,This paper provides  basic  facility of analyicts  like getting number of tweets and retweet_count based on id of user. The Twitter Web Service built on Google cloud platform will give us an exposure to most important areas and tools of cloud computing like processing unstructured data(big data), Apache Flume , Hive and Hadoop Mapreduce, building Rest APIs and deploying them on cloud.
		
1)Create two instances on GCP with 7.5 GB ram and 4 CPUS and configure to make ssh connection to instances or install gcloud utility to make ssh connection to instance . 

 	 instance-1	us-east1-c	35.185.67.36    (master)   (internal ip  according to google cloud 10.142.0.2)
	 instance-2	us-east1-c	35.185.89.152    (slave)   (internal ip  according to google cloud 10.142.0.3)


2) Download jdk-7u80-linux-x64.tar.gz  from   http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html , apache-flume-1.7.0-bin.tar.gz  from https://flume.apache.org/download.html , apache-hive-1.2.1-bin.tar.gz from https://www-us.apache.org/dist/hive/hive-1.2.1/  on local machine.

3) copy   jdk-7u80-linux-x64.tar.gz, apache-flume-1.7.0-bin.tar.gz ,apache-hive-1.2.1-bin.tar.gz to both the instances.

	 *)untar   jdk-7u80-linux-x64.tar.gz  on both instances  and follow the steps.
	  tar -xvf jdk-7u80-linux-x64.tar.gz
 	  export JAVA_HOME=/home/shashi/jdk1.8.0_121
 	  export PATH=$JAVA_HOME/bin:$PATH

4)Installation of Hadoop version 2.7.3  on  master node  

 	*)Uncompress the Hadoo-2.7.3.tar.gz file on master node.
	*)Add master and slave node in /etc/hosts file
     		10.142.0.2   master
	        10.142.0.3   slave
			
	*)Make the password less connection between both of the system.

	*)Set Hadoop bin path and Hadoop Home in ./bashrc  file   for   setting environment variable  in both of the system.
	
	  export HADOOP_HOME=/home/shashi/hadoop-2.7.3
	  export HADOOP_MAPRED_HOME=$HADOOP_HOME
	  export HADOOP_COMMON_HOME=$HADOOP_HOME
	  export HADOOP_HDFS_HOME=$HADOOP_HOME
	  export YARN_HOME=$HADOOP_HOME
	  export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
	  export PATH=$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH

 	*)Modify $HADOOP_HOME/etc/hadoop/slaves and add ip of all the nodes or system . 
 	        10.142.0.2     
		10.142.0.3   
    
        *) Modify $HADOOP_HOME/etc/hadoop/hadoop-ens.sh and add the JAVA_HOME in that file.Do this one on both the system .
    	   export JAVA_HOME=/home/shashi/jdk1.8.0_121

        *)Modify $HADOOP_HOME/etc/hadoop/core-site.xml,$HADOOP_HOME/etc/hadoop/hdfs-site.xml,$HADOOP_HOME/etc/hadoop/yarn-site.xml,$HADOOP_HOME/etc/hadoop/mapred-site.xml  
    
        *)$HADOOP_HOME/etc/hadoop/core-site.xml
    
    	              <configuration>
				<property>
  		      		  <name>fs.defaultFS</name>
     			 	  <value>hdfs://10.142.0.2:9000</value>
   			       </property>
		      </configuration>

 	 *)$HADOOP_HOME/etc/hadoop/hdfs-site.xml

 	 	<configuration>
 			<property>
      		 	 	<name>dfs.replication</name>
      		 	 	<value>2</value>
  	  		</property>
 		</configuration>

         *)$HADOOP_HOME/etc/hadoop/Yarn-site.xml

 			<configuration>
				<property>
      					<name>mapreduce.framework.name</name>
     					 <value>yarn</value>
 		 	 	</property>
 		 		<property>
      					<name>yarn.resourcemanager.hostname</name>
     					 <value>10.142.0.2</value>
 	  			</property>
				<property>
    					  <name>yarn.resourcemanager.address</name>
    					  <value>10.142.0.2:8032</value>
   				</property>
	</configuration>

	*)$HADOOP_HOME/etc/hadoop/mapred-site.xml

			<configuration>
				<property>
     				 	<name>yarn.nodemanager.aux-services</name>
     				 	<value>mapreduce_shuffle</value>
  				 </property>
			</configuration>


   	*) Uncompress the Hadoo-2.7.3.tar.gz file on slave node & copy core-site.xml,hdfs-site.xml,Yarn-site.xml,mapred-site.xml in $HOME/hadoop-2.7.3/etc/hadoop path on second instance (35.185.89.152 ) 

  	*)copy  ~/.bashrc   in    $HOME path  on second instance (35.185.89.152 ) 
	
    	*)Run the below command on first instance   35.185.67.36 
    			hadoop namenode -format
   			start-dfs.sh 
   			start-yarn.sh 


5)Installation of Apache Flume version 1.7.0

      *)Untar  apache-flume-1.7.0-bin.tar.gz
      *)Add the configuration twit.conf file in conf folder(Creation of twit.conf file is given later).
      *)Execute the command 
		./bin/flume-ng agent --conf ./conf/ -f  conf/twit.conf  Dflume.root.logger=DEBUG,console -n TwitterAgent 

6)Installation of Hive

	*)Untar  apache-hive-1.2.1-bin.tar.gz
	*)Download hive-serdes-1.0-SNAPSHOT.jar on both instances
	
		export HIVE_HOME=/home/shashi/apache-hive-1.2.1-bin
		export PATH=$HIVE_HOME/bin:$PATH
		
	*)Add the configuration file  $HIVE_HOME/conf/hive-site.xml
	
	<configuration>
 	 			<property>
 						<name>fs.default.name</name>
 						 <value>hdfs://10.142.0.2:9000</value>
            			</property>
  	 			<property>
						<name>mapred.job.tracker</name>
						<value>10.142.0.2:9001</value>
				</property>
				<property>
 						<name>hive.aux.jars.path</name>
						 <value>file:///home/shashi/hive-serdes-1.0-SNAPSHOT.jar</value>
				</property>
	</configuration>

	*)Installation of Derby DB 
         Derby is default database used by HIVE which comes with Hive tool.
		

7)Configuration  of each tool 

       	*) Hadoop Configuration 
         	hdfs -mkdir  /user/Hadoop/twitter_data

	*)Apache Flume  Configuration 
	*)create twit.conf file in conf folder in root directory of apache-flume

		TwitterAgent.sources = Twitter
		TwitterAgent.channels = FileChannel
		TwitterAgent.sinks = HDFS
		TwitterAgent.sources.Twitter.type = org.apache.flume.source.twitter.TwitterSource
		TwitterAgent.sources.Twitter.channels = FileChannel
		TwitterAgent.sources.Twitter.consumerKey = SqWu0uWAtrg6p6ROqAptbJlva
		TwitterAgent.sources.Twitter.consumerSecret = bPtncFTPBUwvbzLnW5yv0VmtMMPkcnst4r8HrAkZwxG5uuB1xo
		TwitterAgent.sources.Twitter.accessToken = 2235585415-RLCVufXlDhzcXsAe4DknXnmoQV7ihlXpvB5Tevk
		TwitterAgent.sources.Twitter.accessTokenSecret = XFbV51FS0BtkBXe7jOA2qe8Sd8kirNTkur9hwb5JZdqCy
		TwitterAgent.sources.Twitter.maxBatchSize = 50000
		TwitterAgent.sources.Twitter.maxBatchDurationMillis = 100000
		TwitterAgent.sources.Twitter.keywords = Apache, Hadoop, Mapreduce, hadooptutorial, Hive, Hbase, MySql
		TwitterAgent.sinks.HDFS.channel = FileChannel
		TwitterAgent.sinks.HDFS.type = hdfs
		TwitterAgent.sinks.HDFS.hdfs.path = hdfs://10.142.0.2:9000/user/Hadoop/twitter_data
		TwitterAgent.sinks.HDFS.hdfs.fileType = DataStream
		TwitterAgent.sinks.HDFS.hdfs.writeFormat = Text
		TwitterAgent.sinks.HDFS.hdfs.batchSize = 200000
		TwitterAgent.sinks.HDFS.hdfs.rollSize = 0
		TwitterAgent.sinks.HDFS.hdfs.rollCount = 2000000
		TwitterAgent.channels.FileChannel.type = file
		TwitterAgent.channels.FileChannel.checkpointDir =  /home/shashi/apache-flume-1.7.0-bin/var/log/flume/checkpoint/
		
	*)Execute the command

		bin/flume-ng agent --conf ./conf/ -f  conf/twit.conf  Dflume.root.logger=DEBUG,console -n TwitterAgent  >> log1 2>&1  &
		
	*)configuration of Apache-hive  tool 

	*)create  TwitterDataAvroSchema.avsc file in home directory of linux user account and add the below contents 

   
		{"type":"record",
 		"name":"Doc",
 		"doc":"adoc",
 		"fields":[{"name":"id","type":"string"},
           	{"name":"user_friends_count","type":["int","null"]},
          	{"name":"user_location","type":["string","null"]},
          	{"name":"user_description","type":["string","null"]},
           	{"name":"user_statuses_count","type":["int","null"]},
          	{"name":"user_followers_count","type":["int","null"]},
          	{"name":"user_name","type":["string","null"]},
          	{"name":"user_screen_name","type":["string","null"]},
          	{"name":"created_at","type":["string","null"]},
          	{"name":"text","type":["string","null"]},
           	{"name":"retweet_count","type":["long","null"]},
          	{"name":"retweeted","type":["boolean","null"]},
         	{"name":"in_reply_to_user_id","type":["long","null"]},
                {"name":"source","type":["string","null"]},
         	{"name":"in_reply_to_status_id","type":["long","null"]},
           	{"name":"media_url_https","type":["string","null"]},
                {"name":"expanded_url","type":["string","null"]}
        	 ]
	         }

	
	*)create  createtable.hql file in  home directory of linux  user and add the below contents 
		
		  CREATE TABLE tweets
		  ROW FORMAT SERDE
		  'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
		  STORED AS INPUTFORMAT
		  'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
		  OUTPUTFORMAT
		  'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
 		  TBLPROPERTIES ('avro.schema.url'='file:///home/shashi/TwitterDataAvroSchema.avsc');
		  LOAD DATA INPATH '/user/Hadoop/twitter_data/FlumeData.*'   INTO TABLE tweets;
			
			
	*) created tweets_avro_table.hql file in home directory of linux  user and add the below contents 

		
		 CREATE EXTERNAL TABLE tweets_avro_table (
       		 id                      string,
       		 user_friends_count      int   ,
       		 user_location           string,
       		 user_description        string,
        	 user_statuses_count     int   ,
        	 user_followers_count    int   ,
       		 user_name               string,
                 user_screen_name        string,
                 created_at              string,
                 text                    string,
                 retweet_count           bigint,
                 retweeted               boolean,
                 in_reply_to_user_id     bigint,
                 source                  string,
        	 in_reply_to_status_id   bigint,
        	 media_url_https         string,
       	         expanded_url            string
                 )
		STORED AS AVRO LOCATION '/user/externaltables/';
		INSERT OVERWRITE TABLE tweets_avro_table SELECT * FROM tweets LIMIT 2500;




	*) Script which runs  for Rest service which helps to get the  twits   based on timestamp.

		 hive -f  /home/shashi/screatetable.hql 
	         hive -f  /home/shashi/create_tweets_avro_table.hql
		 echo "select  id ,"'"|"'" ,text  from  tweets_avro_table  where created_at= \"$1\" "> query1.hql
		 hive  -f  query1.hql  > output1


	*) Script which runs  for Rest service which helps to get the  number  twits  based on  id of user.

		 hive -f  /home/shashi/screatetable.hql 
		 hive -f  /home/shashi/create_tweets_avro_table.hql
		 echo "select   id , \"|\" ,count(text)    from  tweets_avro_table  where  id=$1 GROUP BY  id  "  > /home/shashi/query2.hql
		 hive  -f   /home/shashi/query2.hql  >  /home/shashi/output2

	 *) Script which runs  for Rest service which helps to get the  number of retweet_count  based on  id of user.

		hive -f  /home/shashi/screatetable.hql 
		hive -f  /home/shashi/create_tweets_avro_table.hql
		echo "select  id, \"|\" ,retweet_count   from  tweets_avro_table  where  id  in  ($1,$2)" > /home/shashi/query3.hql
	        hive -f  /home/shashi/query3.hql  >  output3

          Rest services 
	  
          *)Eclipse configuration  & created the dynamic web project and  developed  the rest services  for each operation 
	  *)Compile UserManagement  project by putting whole  project in eclipse.Put all the jar file in lib folder as well as build path of eclipse
	  *)Create war file by using Export option of Eclipse.
	  *)Download the  apache-tomcat-8.0.30   from https://tomcat.apache.org/download-80.cgi
          *)Deploy the war file on apache-tomcat server  which is deployed on instance where hadoop and hive is installed
	 

7)View the output

          *)Download the postman tool from   'https://www.getpostman.com/ and run the example url which is given below .Screen shot of output is added in github account
          *)URL : to get the data based on Timestamp

                  http://35.185.67.36:8080/UserManagement/rest/UserService/user/{timestamp}

                  Example:
                  http://35.185.67.36:8080/UserManagement/rest/UserService/users/2017-03-18T21:27:38Z
		  Sample output : output1
		   
          *)URL : to get the data based on  id of user
 
                  http://35.185.67.36:8080/UserManagement/rest/UserService/user/{id}
                  Example: 
                  http://35.185.67.36:8080/UserManagement/rest/UserService/user/843212147148709888
		  Sample output : output2

          *)URL : to get the data based on  id of user
	  
                   http://35.185.67.36:8080/UserManagement/rest/UserService/user/{id1}/{id2}
		   Example: 
                   http://35.185.67.36:8080/UserManagement/rest/UserService/user/843212151330344961/843212151326232576
		   Sample output  : output3
		   
           

          









