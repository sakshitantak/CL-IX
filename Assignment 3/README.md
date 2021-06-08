How to run and compile this code in Windows
Assuming your jdk version set in JAVA_HOME supports CORBA (Note : jdk1.8.0_281 that I used is compatible with CORBA framework)
1. Open a terminal, lets call it T1. Fire this command for idlj "idlj -fall Reverse.idl". If you get an error, try this " '</path/to/idlj.exe>' -fall Reverse.idl"
("C:/Program Files/Java/jdk1.8.0_281/bin/idlj.exe" -fall Reverse.idl)

2. In T1, fire "javac *.java ReverseApp/*.java" to generate the java Helper codes. If this doesn't work, try this " '<path/to/javac.exe>' *.java ReverseApp/*.java"
("C:/Program Files/Java/jdk1.8.0_281/bin/javac.exe" *.java ReverseApp/*.java)

3. In T1, fire "tnameserv -ORBInitialPort 1050&". If this does not work, replace tnameserve with path to tnameserv
("C:/Program Files/Java/jdk1.8.0_281/bin/tnameserv.exe" -ORBInitialPort 1050&)

4. Open another terminal, lets call it T2. Fire command "java ReverseServer -ORBInitialPort 1050&" If this does not work, replace java with the path to java to run the server
("C:/Program Files/Java/jdk1.8.0_281/bin/java.exe" ReverseServer -ORBInitialPort 1050&)

5. Open another terminal, say T3. Fire command "java ReverseClient -ORBInitialPort 1050" to run the client
("C:/Program Files/Java/jdk1.8.0_281/bin/java.exe" ReverseClient -ORBInitialPort 1050)

(Note : idlj.exe, java.exe, javac.exe will be generally found in Program Files/Java/jdk1.8.0_281 in my case.)