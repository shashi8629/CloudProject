bin/flume-ng agent --conf ./conf/ -f  conf/twit.conf  Dflume.root.logger=DEBUG,console -n TwitterAgent  >> log1 2>&1  &
