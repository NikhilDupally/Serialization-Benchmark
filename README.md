# JSON vs Protocol Buffers vs FlatBuffers - Benchmarking

## Overview
This repository contains benchmark tests comparing the serialization and deserialization performance of JSON, Protocol Buffers (ProtoBuf), and FlatBuffers in Java. The goal is to analyze their efficiency in terms of speed and size for different payloads.

## Blog Post
For a detailed explanation of the benchmarking process, results, and real-world use cases, refer to the blog post:
[Blog](#)

## Benchmarking Setup
### Technologies Used
- **JSON:** Jackson Library
- **Protocol Buffers:** Google's ProtoBuf Java library
- **FlatBuffers:** Google's FlatBuffers Java library
- **Benchmarking Tool:** JMH (Java Microbenchmark Harness)

### Test Data
The benchmark tests use a simple object with multiple fields (integers, strings, nested objects) to simulate real-world serialization and deserialization scenarios.

### Performance Metrics
The benchmarks measure:
- **Serialization Time:** The time taken to convert an object into a byte array.
- **Deserialization Time:** The time taken to reconstruct an object from a byte array.
- **Serialized Size:** The size of the serialized data.

## Running the Benchmarks
### Prerequisites
1. Install Java (JDK 8+ recommended)
2. Install Maven
3. Clone this repository:
   ```sh
   git clone https://github.com/NikhilDupally/Serialization-Benchmark.git
   cd Serialization-Benchmark
   ```

### Execute Benchmarks
Run the JMH benchmarks using Maven:
```sh
mvn clean install
mvn exec:java -Dexec.mainClass=core.BaseJHMBenchmark
```


## Conclusion
- JSON is the most human-readable but slowest in performance.
- Protocol Buffers provide a balance of speed and compactness.
- FlatBuffers offer the fastest deserialization with zero-copy access, making them ideal for real-time applications.

## References
- [JMH (Java Microbenchmark Harness)](https://openjdk.org/projects/code-tools/jmh/)
- [Jackson Library](https://github.com/FasterXML/jackson)
- [Google Protocol Buffers](https://developers.google.com/protocol-buffers/)
- [Google FlatBuffers](https://github.com/google/flatbuffers)

## Author
[Nikhil Dupally](www.linkedin.com/in/dupally-nikhil)
