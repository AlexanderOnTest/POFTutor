Start-Process powershell {java -jar "C:\Grid\selenium-server-standalone-3.12.0.jar" -role hub}

Start-Process powershell {java -jar "C:\Grid\selenium-server-standalone-3.12.0.jar" -port 5560 -role node -hub http://localhost:4444/grid/register -nodeConfig "C:\Grid\nodeconfig.json"}