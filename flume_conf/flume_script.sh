cd $FLUME_HOME 
$ bin/flume-ng agent --conf ./conf/ -f conf/twitter.conf 
Dflume.root.logger=DEBUG,console -n TwitterAgent
