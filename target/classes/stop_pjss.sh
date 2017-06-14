#!/bin/sh
pid=`jps|grep pjss.jar|awk '{print $1}'`
echo "结束进程:"`jps|grep pjss.jar`
kill -9  $pid
