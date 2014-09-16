JFLAGS = -g
JC = javac
JVM = java
MAIN = OSPrint
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
 
CLASSES = \
	OSPrint.java \

default: classes
 
classes: $(CLASSES:.java=.class)
clean:
	$(RM) *.class
run: classes
	$(JVM) $(MAIN)