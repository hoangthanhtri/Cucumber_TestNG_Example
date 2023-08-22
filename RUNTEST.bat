@echo
call mvn clean test -Dgroups="smoke,regression" -DthreadCount="2" -DbrowserType="firefox" -DisHeadlass="false"
echo After build
start report.html
