package util;

import models.BenchmarkResult;
import benchmark.SerializationBenchmark;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.SwingUtilities;

import javax.swing.*;
import java.util.Map;

public class BenchmarkUtil {
    public static void printResults(String format, long serializationTime, long deserializationTime, int dataSize) {
        System.out.println(format + " - Serialization Time: " + serializationTime / SerializationBenchmark.ITERATIONS + " ns");
        System.out.println(format + " - Deserialization Time: " + deserializationTime / SerializationBenchmark.ITERATIONS + " ns");
        System.out.println(format + " - Data Size: " + dataSize + " bytes");
    }

    public static void generateCharts(Map<String, BenchmarkResult> results) {
        SwingUtilities.invokeLater(() -> {
            generateChart(results, "Serialization Time", "Serialization Time (ns)", BenchmarkResult::getSerializationTime);
            generateChart(results, "Deserialization Time", "Deserialization Time (ns)", BenchmarkResult::getDeserializationTime);
            generateChart(results, "Data Size", "Size (bytes)", BenchmarkResult::getDataSize);
        });
    }

    static void generateChart(Map<String, BenchmarkResult> results, String title, String yAxisLabel, java.util.function.ToLongFunction<BenchmarkResult> valueFunction) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, BenchmarkResult> entry : results.entrySet()) {
            dataset.addValue((double) valueFunction.applyAsLong(entry.getValue()) / SerializationBenchmark.ITERATIONS, title, entry.getKey());
        }
        JFreeChart chart = ChartFactory.createBarChart(title, "Format", yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }

        public static void generateCharts(Map<String, Long> results, String title, String yAxisLabel) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Long> entry : results.entrySet()) {
                dataset.addValue(entry.getValue(), title, entry.getKey());
            }

            JFreeChart chart = ChartFactory.createBarChart(title, "Format", yAxisLabel, dataset);
            JFrame frame = new JFrame(title);
            frame.add(new ChartPanel(chart));
            frame.pack();
            frame.setVisible(true);
        }
}