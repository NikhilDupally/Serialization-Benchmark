package benchmark;

import core.BaseBenchmark;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BenchmarkResult;
import models.SampleData;
import util.BenchmarkUtil;

public class JsonBenchmark extends BaseBenchmark {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SampleData sample = new SampleData("Test", 123, true);


    @Override
    public void runBenchmark() throws Exception {
        long startTime = System.nanoTime();
        String jsonString = "";
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            jsonString = objectMapper.writeValueAsString(sample);
        }
        long serializationTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            objectMapper.readValue(jsonString, SampleData.class);
        }
        long deserializationTime = System.nanoTime() - startTime;

        int dataSize = jsonString.getBytes().length;
        SerializationBenchmark.results.put("JSON", new BenchmarkResult(serializationTime, deserializationTime, dataSize));
        BenchmarkUtil.printResults("JSON", serializationTime, deserializationTime, dataSize);
    }
}