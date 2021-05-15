ifeq ($(OS),Windows_NT)
	COMPILE1=javac -cp .;./src/*; -d . src/Printer.java
	COMPILE2=javac -cp .;./src/*; -d . src/Client.java
	COMPILE3=javac -cp .;./src/*;./lib/commons-net-3.3.jar; -d . src/FTP_Client.java
	COMPILE4=javac -cp .;./src/*; -d . src/DataBase.java
	COMPILE5=javac -cp .;./src/*; -d . src/WindowsData.java
	COMPILE6=javac -cp .;./src/*; -d . src/Main.java
	COMPILE7=jar cfm ../Clients.jar META-INF/manifest.mf *

	RUN1=java -cp .;./src; com.data.Client
	RUN2=java -cp .;./src; com.data.DataBase.java
	RUN3=java -cp .;./src; com.window.WindowsData
	RUN4=java -cp .;./src; com.execute.Main
	RUN5=java -cp .;./src; com.printer.Printer
	RUN6=java -jar ../Clients.jar

	CLEAR=remove-item ./com

	CLEAN=cls

# Linux or MacOS
else
	UNAME_S := $(shell uname -s)

	# Linux
	ifeq ($(UNAME_S), Linux)
		COMPILE1=javac -cp .:./src/*: -d . src/Client.java
		COMPILE2=javac -cp .:./src/*:./lib/commons-net-3.3.jar: -d . src/FTP_Client.java
		COMPILE3=javac -cp .:./src/*: -d . src/DataBase.java
		COMPILE4=javac -cp .:./src/*: -d . src/WindowsData.java
		COMPILE5=javac -cp .:./src/*: -d . src/Main.java
		COMPILE6=javac -cp .:./src/*: -d . src/Printer.java
		COMPILE7=jar cfm ../Clients.jar META-INF/manifest.mf *

		RUN1=java -cp ./src: com.data.Client
		RUN2=java -cp ./src: com.data.DataBase.java
		RUN3=java -cp ./src: com.window.WindowsData
		RUN4=java -cp ./src: com.execute.Main
		RUN5=java -cp ./src: com.printer.Printer
		RUN6=java -jar ../Clients.jar

		CLEAR=rm -rf ./com

		CLEAN=clear
	endif

	# MacOS
	ifeq ($(UNAME_S),Darwin)
		COMPILE1=javac -cp .:./src/*: -d . src/Client.java
		COMPILE2=javac -cp .:./src/*:./lib/commons-net-3.3.jar: -d . src/FTP_Client.java
		COMPILE3=javac -cp .:./src/*: -d . src/DataBase.java
		COMPILE4=javac -cp .:./src/*: -d . src/WindowsData.java
		COMPILE5=javac -cp .:./src/*: -d . src/Main.java
		COMPILE6=javac -cp .:./src/*: -d . src/Printer.java
		COMPILE7=jar cfm ../Clients.jar META-INF/manifest.mf *

		RUN1=java -cp ./src: com.data.Client
		RUN2=java -cp ./src: com.data.DataBase.java
		RUN3=java -cp ./src: com.window.WindowsData
		RUN4=java -cp ./src: com.execute.Main
		RUN5=java -cp ./src: com.printer.Printer
		RUN6=java -jar ../Clients.jar

		CLEAR=rm ./com

		CLEAN=clear
	endif
endif

all:
	${CLEAN}
	${COMPILE1}
	${COMPILE2}
	${COMPILE3}
	${COMPILE4}
	${COMPILE5}
	${COMPILE6}
	${COMPILE7}
	${RUN6}

compile:
	${COMPILE1}
	${COMPILE2}
	${COMPILE3}
	${COMPILE4}
	${COMPILE5}
	${COMPILE6}
	${COMPILE7}

run:
	${RUN6}

clear:
	${CLEAR}