sleep 30

/opt/mssql-tools/bin/sqlcmd -U sa -P abc123!!@ -Q "CREATE DATABASE OrderServiceDB;"
/opt/mssql-tools/bin/sqlcmd -U sa -P abc123!!@ -d OrderServiceDB -i /docker/Order.sql