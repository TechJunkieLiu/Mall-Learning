#!/bin/bash

programName="mall-learning.jar"
programParam="JVM_OPTIONS=-Xms1024M -Xmx2048M -XX:PermSize=128M -XX:MaxPermSize=256M"
mainLogFile="./logs/log_info.log"

echo "$programName 程序脚本执行"
# 停止程序方法，正在关闭返回1，关闭异常返回-1，已经关闭返回0
stopFunc(){
	# 判断是否有pid文件
	if [ -r "pid" ];then
		# 读取pid
		pid=$(cat pid)
		if [ "$pid" == "" ];then
			echo "未读取到pid的值，请手动kill进程并删除pid文件"
			return -1;
		else
			# 判断进程id是否存在且为java进程，小于1表示不存在，否则表示存在，不存在说明以关闭，删除pid文件，存在用kill -15结束进程
			if [ $(ps --no-heading $pid | grep java | wc -l) -lt 1 ];then
				echo "id为 $pid 的进程已关闭，删除pid文件"
				rm -rf pid
				return 0
			else
				echo "开始关闭进程$pid"
				kill -15 $pid
				return 1
			fi
		fi
	else
		# 不存在说明程序未启动，直接返回成功
		echo "未找到pid文件，程序未启动"
		return 0
	fi
}
# 启动程序方法
startFunc(){
	if [ "$1" == "debug" ];then
		nohup java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n -jar $programName $programParam >/dev/null 2>&1 &
	else
		nohup java -jar $programName $programParam >/dev/null 2>&1 &
	fi
	# 将进程id保存到pid文件中
	echo "$!" > pid
	echo "启动成功"
}
# 取脚本参数
status=$1
debug=$2
# 如果脚本参数为空或start，表示启动程序0
if [ "$status" == "" ] || [ "$status" == "start" ];then
	echo "准备停止旧进程"
	while true;do
		stopFunc
		if [ $? -eq 0 ];then
			break
		# -1表示启动失败，直接退出
		elif [ $? -eq -1 ];then
			exit
		fi
		sleep 1s
	done
	echo "准备启动新进程"
	startFunc $debug
	count=1
	while [ ! -r "$mainLogFile" ];do
		echo "第$count 次尝试读取日志文件$mainLogFile"
		if [ $((count)) -ge 8 ];then
			echo "无法读取日志文件$mainLogFile ，请确定日志文件路径正确且具有可读权限"
			exit
		fi
		count=$(($count+1))
		sleep 1s
	done
	tail -f $mainLogFile
elif [ "$status" == "startNoLog" ];then
	echo "准备停止旧进程"
	while true;do
		stopFunc
		if [ $? -eq 0 ];then
			break
		# -1表示启动失败，直接退出
		elif [ $? -eq -1 ];then
			exit
		fi
		sleep 1s
	done
	echo "准备启动新进程"
	startFunc $debug
# 如果脚本参数为stop，只停止进程，不重新启动
elif [ "$status" == "stop" ];then
	echo "准备停止进程"
	while true;do
		stopFunc
		if [ $? -eq 0 ];then
			break
		elif [ $? -eq -1 ];then
			exit
		fi
		sleep 1s
	done
else
	echo "请输入参数'start'、'startNoLog'或'stop'(不传参数则默认为'start')"
	exit
fi