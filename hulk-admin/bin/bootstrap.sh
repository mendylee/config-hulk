#!/bin/sh

JAVA_HOME="/opt/module/java/current"
JAVA_OPTS="-server -Xms512m -Xmx512m -XX:MaxPermSize=256m -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5"

APP_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )/../.." && pwd )" 
APP_NAME="$( echo "$APP_HOME" | sed -r 's@^/.*/([^/]+)/?@\1@g' )"
APP_PID_FILE=$APP_HOME/shared/pids/$APP_NAME.pid

MAIN_CLASS=com.xiangrikui.hulk.web.HulkAdminApplication


CLASSPATH=$APP_HOME/current/config:$APP_HOME/current/webapp

for jarfile in "$APP_HOME"/current/lib/*.jar
do
   CLASSPATH="$CLASSPATH":"$jarfile"
done

###################################
#(函数)判断程序是否已启动
#
#说明：
# @RET  : 0 => 程序正在运行
#         1 => 程序未运行
#         2 => PID文件存在，但不存在程序进程号
###################################
checkStatus(){
    if [ -f "$APP_PID_FILE" ]; then
       if  [ -z "`cat $APP_PID_FILE`" ];then
        echo "ERROR: Pidfile '$APP_PID_FILE' exists but contains no pid"
        return 2
       fi
       
       PID=`cat $APP_PID_FILE`
       
       RET=`ps -p $PID|grep java`
       
       if [ -n "$RET" ];then
         return 0;
       else
         return 1;
       fi
    else
         return 1;
    fi
}


###################################
#(函数)启动程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用checkpid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
#注意: "nohup 某命令 >/dev/null 2>&1 &" 的用法
###################################
start(){
    if ( checkStatus );then
      PID=`cat $APP_PID_FILE`
      echo "INFO: process with pid '$PID' is already running"
      exit 0
    fi

    nohup $JAVA_HOME/bin/java -classpath $CLASSPATH $MAIN_CLASS >/dev/null 2>&1 &
    
    echo $! > $APP_PID_FILE
}

###################################
#(函数)停止程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则开始执行停止，否则，提示程序未运行
#3. 使用kill -9 pid命令进行强制杀死进程
#4. 执行kill命令行紧接其后，马上查看上一句命令的返回值: $?
#5. 如果步骤4的结果$?等于0,则打印[OK]，否则打印[Failed]
#6. 为了防止java程序被启动多次，这里增加反复检查进程，反复杀死的处理（递归调用stop）。
#注意：echo -n 表示打印字符后，不换行
#注意: 在shell编程中，"$?" 表示上一句命令或者一个函数的返回值
###################################
stop() {
   if( checkStatus );then
      PID=`cat $APP_PID_FILE`

      echo -n "stopping $APP_MAINCLASS ...($PID) "

      kill -9 $PID

      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[FAILED]"
      fi

      if( checkStatus ); then
         stop
      fi
   else
      echo "================================"
      echo "warn: $APP_MAINCLASS is not running"
      echo "================================"
   fi

   rm -f $APP_PID_FILE >/dev/null 2>&1
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status|info}"
     exit 1
esac
exit 0