## WINDOW

1. DB 실행
> java -cp "C:\Program Files (x86)\H2\bin\h2-2.2.224.jar" org.h2.tools.Server -tcp -tcpPort 9096 -tcpAllowOthers -web -webPort 8082 -baseDir "C:\Program Files (x86)\H2\data" \
> net start mariadb
2. 레디스 실행
> redis-cli.exe 실행
3. 카프카 실행
> kafka-storage.bat format -t 040150f3-03e9-4e42-a502-72b5e10339b4 -c C:\kafka\config\server.properties \
> 스토리지 초기화 카프카폴더 \
> bin\windows\kafka-server-start.bat config\server.properties
> bin\windows\kafka-server-stop.bat config\server.properties
