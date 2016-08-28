# JMP-2016-NP-AS
Java Mentoring Program 2016. Mentor -  Nickolay Petrash, Mentee - Aliaksandr Semiankevich

##Modules:

1. Architecture: Principle of software development *(5 Jul 2016)*  
2. Backend: Memory Management and Garbage Collection *(26 Jul 2016)*
3. Backend: Classloading *(2 Aug 2016)*
4. Backend: Multitreading: Classic Model and Concurrency *(16 Aug 2016)*
5. Java: Troubleshooting *(23 Aug 2016)*
6. Infrastructure: JDBC  Advanced Techniques *(6 Sep 2016)*
7. Infrastructure: Build: Maven, Gradle *(13 Sep 2016)* 
8. Infrastructure: Continuous Integration Concepts (Jenkins) *(27 Sep 2016)*
9. Infrastructure: Unit testing *(18 Oct 2016)*
10. Backend: Web-Services *(1 Nov 2016)*

##Module 1. Architecture: Principle of software development

**Task**: Create modular project with using DRY principle. You could use any of build managers (ant, maven, gradle and etc.)

**Solution**: Created project *"Library"*. This project consist of 3 modules: [library-app](/library-app), [library-core](/library-core) and [library-stat](/library-stat).

##Module 2. Backend: Memory Management and Garbage Collection

**Task 1**: Take your application and reduce typical -Xmx in 2x. Try different GC algorithms described in presentation. Compare the results with the GC log output. Run it on Java 7 and 8. After that tune Heap and PermGen (or play with Metaspace) and try to utilize and uberize free memory.
Try to reach next goals if possible:
- High throughput in a single processor environment
- High throughput in a multi-processor environment
- High throughput and latency below one second in a multi-processor environment
- Lowest possible latency in a multi-processor environment

**Task 2**: Write your own StatelessClassLoader which will load only stateless classes (without fields) from .jar file specified as input parameter (). Don’t forget provide test code.
Close

**Solution**: Task implemented in [stateless-classloader](/stateless-classloader) project. Test jar implemented in [stateless-classloader-test-data](/stateless-classloader-test-data) project. 
Example for StatelessClassLoader is located in [Run.java](/stateless-classloader/src/main/java/com/epam/jmp/classloader/Run.java) class.

##Module 4. Backend: Multithreading: Classic Model and Concurrency

**Task**: Using suggested class 'Car' create app, which will launch race for several different cars.

1. Create cars with different parameters. Start cars simultaneously and parallel. Observe which finishes first.

2. When the race is over - output the winner message 'Winner is XXXXX!'.

3. Disqualify one of cars from the race after 5 seconds by interrupting it's thread - car code needs to be modified for that.

```java
public class Car implements Runnable {
        
    private static final long MAX_DISTANCE = 10000;

    Logger log = Logger.getLogger(getClass());
        
    private long friction; 
    private long distance; 
        
    private String name;
        
    public Car(String name, long friction) {
        this.name = name;
        this.friction = 100;
    }

    @Override
    public void run() {
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                log.info(name + " " + distance);
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
    }

}
```

##Module 5. Java: Troubleshooting

**Task 1 (Simple Deadlock)**: Implement java application to reproduce deadlock below.
Collect thread dumps.

Results of the task:
-	Java source code
-	Collected thread dumps

**Task 1 (Realistic Deadlock)**: Implement java application to reproduce deadlock below.
Collect thread dumps.
 
Results of the task:
-	Java source code
-	Collected thread dumps
