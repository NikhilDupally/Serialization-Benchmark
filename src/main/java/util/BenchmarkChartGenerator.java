package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkChartGenerator {

    public static void main(String[] args) throws IOException {
        String jsonFilePath = "benchmark_results.json";
        Map<String, Double> serializationResults = new HashMap<>();
        Map<String, Double> deserializationResults = new HashMap<>();

        parseBenchmarkResults(jsonFilePath, serializationResults, deserializationResults);

        generateChart(serializationResults, "Serialization Performance", "Serialization Time (ns/op)");
        generateChart(deserializationResults, "Deserialization Performance", "Deserialization Time (ns/op)");
    }

    private static void parseBenchmarkResults(String jsonFilePath, Map<String, Double> serializationResults, Map<String, Double> deserializationResults) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(jsonFilePath));

        for (JsonNode node : rootNode) {
            String benchmarkName = node.get("benchmark").asText();
            double score = node.get("primaryMetric").get("score").asDouble();
            String mode = node.get("mode").asText();

            if (benchmarkName.contains("deserialize")) {
                deserializationResults.put(benchmarkName.replace("jmhbenchmark.", ""), score);
            } else if (benchmarkName.contains("serialize")) {
                serializationResults.put(benchmarkName.replace("jmhbenchmark.", ""), score);
            }
        }
    }

    private static void generateChart(Map<String, Double> results, String chartTitle, String yAxisLabel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : results.entrySet()) {
            dataset.addValue(entry.getValue(), "Performance", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,
                "Benchmark",
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        displayChart(chart, chartTitle);
    }

    private static void displayChart(JFreeChart chart, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}

