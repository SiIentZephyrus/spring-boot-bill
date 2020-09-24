#!/bin/sh
echo '准备进行部署项目'
nohup java -jar spring-boot-bill-0.0.1-SNAPSHOT.jar --server.port=80 &
