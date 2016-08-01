# JMP-2016-NP-AS
Java Mentoring Program 2016. Mentor -  Nickolay Petrash, Mentee - Aliaksandr Semiankevich

##Modules:

1. Architecture: Principle of software development *(5 Jul 2016)*  
2. Backend: Memory Management and Garbage Collection *(26 Jul 2016) *
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

##Module 2. Backend: Memory Management and Garbage Collection

**Task 1**: Take your application and reduce typical -Xmx in 2x. Try different GC algorithms described in presentation. Compare the results with the GC log output. Run it on Java 7 and 8. After that tune Heap and PermGen (or play with Metaspace) and try to utilize and uberize free memory.
Try to reach next goals if possible:
- High throughput in a single processor environment
- High throughput in a multi-processor environment
- High throughput and latency below one second in a multi-processor environment
- Lowest possible latency in a multi-processor environment

**Task 2**: Write your own StatelessClassLoader which will load only stateless classes (without fields) from .jar file specified as input parameter (). Don’t forget provide test code.
Close
