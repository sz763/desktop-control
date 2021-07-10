# desktop-control
Application for controlling desktop i.g.:
1. Mouse move
1. Mouse click
1. Send text code
1. Send backspace

Currently I use it for controlling my raspberry pi desktop from my phone.
How to achive it:
1. Build jar `mvn clean package`
1. Upload `jar` file with `web` directory to remote host
1. Run application: `java -jar desktop-control.jar`
1. Open in browser `remote_host_ip:8080`
1. Enjoy
If have a question `Why? There are a lot of tools with same and more functionality?`
The answer is: because I can :)
