package jmhbenchmark;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.SampleData;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JsonBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(JsonBenchmark.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SampleData sample = new SampleData("Test", 123, true);
    private String jsonString;

    @Setup(Level.Invocation)
    public void setup() throws Exception {
        jsonString = objectMapper.writeValueAsString(sample);
    }

    @Benchmark
    public String serialize() throws Exception {
        return objectMapper.writeValueAsString(sample);
    }

    @Benchmark
    public SampleData deserialize() throws Exception {
        return objectMapper.readValue(jsonString, SampleData.class);
    }
}

