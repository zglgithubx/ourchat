#!/bin/bash

# docker 容器名字或者jar名字，这里都命名为这个
SERVER_NAME=ourchat

#这里的JAR_PATH为jar包所在位置
JAR_PATH=./target/demo-0.0.1-RELEASE.jar

profile=$2
port=$3
nettyPort=$4
#Xms=$4
#Xmx=$5

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [build|start|stop|restart|status|pull] [profile] [port] [nettyPort]"
    exit 1
}
build(){
  echo "----------mvn clean package -Dmaven.test.skip=true---------"
  mvn clean package -Dmaven.test.skip=true
  if [ $? -ne 0 ]; then
    exit
  fi
  echo "----------Preparing start application ---------"
  is_exist
  if [ $? -eq "0" ]; then
    restart
  else
    start
  fi
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $JAR_PATH|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${SERVER_NAME} is already running. pid=${pid} ."
  else
    echo --------Starting application --------
    nohup java -server -Xms512m -Xmx512m -XX:SurvivorRatio=4 -Xss256k -XX:PermSize=256m -XX:MaxPermSize=512m -XX:-DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -jar $JAR_PATH --spring.profiles.active=${profile:-prod} --server.port=${port:-8000} --netty.port=${nettyPort:-8001}> start.log 2>&1 &
    echo --------------Started!---------------
  fi
}

#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
    echo -----------Application Stopped------------
  else
    echo "${JAR_PATH} is not running"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${JAR_PATH} is running. Pid is ${pid}"
  else
    echo "${JAR_PATH} is NOT running."
  fi
}

#重启
restart(){
  stop
  start
}

#mvn
pull(){
  echo "----------git：find status---------"
  git status
  echo "----------git：pull new coads---------"
  git pull origin develop
  if [ $? -ne 0 ]; then
    exit
  fi
  echo "----------mvn clean package -Dmaven.test.skip=true---------"
  mvn clean package -Dmaven.test.skip=true
  if [ $? -ne 0 ]; then
    exit
  fi
  echo "----------Preparing start application ---------"
  is_exist
  if [ $? -eq "0" ]; then
    restart
  else
    start
  fi
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "build")
    build
    ;;
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  "pull")
    pull
    ;;
  *)
    usage
    ;;
esac

