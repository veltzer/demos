copy server-classes\*.class client-classes\
del client-classes\BankImpl_Skel.class
del client-classes\BankImpl.class

javac -d client-classes BankClient.java
pause
