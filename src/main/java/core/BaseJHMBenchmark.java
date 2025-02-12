package core;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ChartGenerator;

import java.io.IOException;
import java.util.Collection;

public class BaseJHMBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(BaseJHMBenchmark.class);

    public static void main(String[] args) throws RunnerException, IOException {

        logger.info("Starting Serialization Benchmark...");

        Options opt = new OptionsBuilder()
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(10)
                .resultFormat(ResultFormatType.JSON)
                .result("benchmark_results.json")
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        ChartGenerator.generateCharts("benchmark_results.json");
    }
}
