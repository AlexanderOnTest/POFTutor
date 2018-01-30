Start-Process powershell {java -jar "C:\Grid\selenium-server-standalone-3.8.1.jar" -role hub}

Start-Process powershell {java -jar "C:\Grid\selenium-server-standalone-3.8.1.jar" -port 5560 -role node -hub http://localhost:4444/grid/register -nodeConfig "C:\Grid\nodeconfig.json"}