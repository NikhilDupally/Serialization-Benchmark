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
import java.util.Iterator;

public class ChartGenerator {

    public static void generateCharts(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(jsonFilePath));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Iterator<JsonNode> elements = rootNode.elements();
        while (elements.hasNext()) {
            JsonNode node = elements.next();
            String benchmarkName = node.get("benchmark").asText();
            double time = node.get("primaryMetric").get("score").asDouble();
            dataset.addValue(time, "Time (ns)", benchmarkName);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Serialization & Deserialization Performance",
                "Benchmark",
                "Time (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        displayChart(chart);
    }

    private static void displayChart(JFreeChart chart) {
        JFrame frame = new JFrame("Benchmark Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}

