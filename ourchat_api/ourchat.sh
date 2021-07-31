#!/bin/bash

# 镜像名字
IMAGE_NAME=centos7_mvn_git_java8

# docker 容器名字或者jar名字，这里都命名为这个
SERVER_NAME=hello-api-java

#这里的JAR_PATH为jar包所在位置
JAR_PATH=./target/demo-0.0.1-RELEASE.jar

profile=$2
port=$3
#Xms=$4
#Xmx=$5

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [init|start|stop|restart|status|pull] [profile] [port]"
    exit 1
}

#初始化——构建镜像和容器(在宿主机执行)
init(){
  #容器id
  CID=$(docker ps | grep "$SERVER_NAME" | awk '{print $1}')
  #镜像id
  IID=$(docker images | grep "$IMAGE_NAME" | awk '{print $3}')
	# 构建docker镜像
	if [ -n "$IID" ]; then
		echo "Exit $SERVER_NAME image，IID=$IID"
	else
		echo "NOT exit $SERVER_NAME image，start build image..."
		# 根据项目个路径下的Dockerfile文件，构建镜像
		docker build -t $IMAGE_NAME .
		echo "$SERVER_NAME image has been builded"
	fi

	if [ -n "$CID" ]; then
			echo "Exit $SERVER_NAME container，CID=$CID.   ---Remove container"
			docker stop $SERVER_NAME   # 停止运行中的容器
			docker rm $SERVER_NAME     ##删除原来的容器
	fi

	# 构建容器
	echo "$SERVER_NAME container,start build..."
	# 运行容器
	 # --name 容器的名字
	 #   -d   容器后台运行
	 #   -p   指定容器映射的端口和主机对应的端口
	 #   -v   将主机的目录挂载到容器的目录中（不可少）
	docker run -e TZ="Asia/Shanghai" -id --name $SERVER_NAME  -p 8087:8087 -p 8086:8086 -v $PWD:/project/$SERVER_NAME $IMAGE_NAME
	echo "$SERVER_NAME container build end"
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
    nohup java -server -Xms512m -Xmx512m -XX:SurvivorRatio=4 -Xss256k -XX:PermSize=256m -XX:MaxPermSize=512m -XX:-DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -jar $JAR_PATH --spring.profiles.active=${profile:-dev} --server.port=${port:-8087} > start.log 2>&1 &

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
  "init")
    init
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

