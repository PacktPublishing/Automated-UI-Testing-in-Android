#!/bin/bash

TestClass=$1

for udid in `$ANDROID_HOME/platform-tools/adb devices | awk 'NR > 1 {print $1}'`; do 
	pid=`ps -x | grep appium | grep -v 'grep' | grep $udid | awk '{print $1}'`
	if [[ "$pid" = "" ]]; then
		prefix=`cat config.properties | grep $udid | cut -d'_' -f 1 | awk '{print}'`
		echo "Prefix: $prefix"
		if [[ "$prefix" = "" ]]; then
			continue
		fi
		port=`cat config.properties | grep 'port' | grep "$prefix" | cut -d'=' -f 2 | awk '{print}'`
		echo "$port"
		bootstrap=$((port + 20))
		echo "$bootstrap"
		appium -a 127.0.0.1 --port $port -bp $bootstrap --default-capabilities "{\"udid\":\"$udid\"}" &
		sleep 20
		export prefix=$prefix;sh gradlew clean test -Dtest.single=$TestClass --stacktrace
		for app_id in `ps -x | grep appium | grep -v 'grep' | grep $udid | awk '{print $1}'`; do kill $app_id; done
		echo "Run completed"
		break
	else
		echo "Device $udid is busy"
	fi
done