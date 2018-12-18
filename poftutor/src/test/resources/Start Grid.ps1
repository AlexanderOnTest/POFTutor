Start-Process powershell {java -jar ".\selenium-server-standalone-3.14.0.jar" -role hub}

Start-Process powershell {java -jar ".\selenium-server-standalone-3.14.0.jar" -port 5560 -role node -hub http://localhost:4444/grid/register -nodeConfig ".\nodeconfig.json"}