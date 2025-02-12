package benchmark;

import core.BaseBenchmark;
import models.BenchmarkResult;
import util.BenchmarkUtil;
import java.util.HashMap;
import java.util.Map;

public class SerializationBenchmark {
    public static final int ITERATIONS = 100000;
    static final Map<String, BenchmarkResult> results = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BaseBenchmark jsonBenchmark = new JsonBenchmark();
        BaseBenchmark protoBenchmark = new ProtoBenchmark();
        BaseBenchmark flatBenchmark = new FlatBufferBenchmark();

        jsonBenchmark.runBenchmark();
        protoBenchmark.runBenchmark();
        flatBenchmark.runBenchmark();

        BenchmarkUtil.generateCharts(results);
    }
}