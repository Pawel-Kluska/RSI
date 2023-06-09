#!/usr/bin/env powershell

Start-Job -ScriptBlock { dotnet run --project "C:\Users\pawel\Desktop\RabbitMq\Send\" }
Start-Job -ScriptBlock { dotnet run --project "C:\Users\pawel\Desktop\RabbitMq\Send2\"} 
