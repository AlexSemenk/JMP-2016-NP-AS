##Module 2. Backend: Memory Management and Garbage Collection

**Task 1**: Take your application and reduce typical -Xmx in 2x. Try different GC algorithms described in presentation. Compare the results with the GC log output. Run it on Java 7 and 8. After that tune Heap and PermGen (or play with Metaspace) and try to utilize and uberize free memory.
Try to reach next goals if possible:
- High throughput in a single processor environment
- High throughput in a multi-processor environment
- High throughput and latency below one second in a multi-processor environment
- Lowest possible latency in a multi-processor environment

**Task 2**: Write your own StatelessClassLoader which will load only stateless classes (without fields) from .jar file specified as input parameter (). Don’t forget provide test code.

**Solution**: Task implemented in [stateless-classloader](/stateless-classloader) project. Test jar implemented in [stateless-classloader-test-data](/stateless-classloader-test-data) project. 
Example for StatelessClassLoader is located in [Run.java](/stateless-classloader/src/main/java/com/epam/jmp/classloader/Run.java) class.