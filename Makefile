default: Main

%: %.java
	javac $@.java
	java -ea $@

clean:
	rm -f *.class