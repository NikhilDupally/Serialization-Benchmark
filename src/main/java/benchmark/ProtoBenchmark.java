package benchmark;

import com.example.protobuf.SampleDataProto;
import com.google.protobuf.InvalidProtocolBufferException;
import core.BaseBenchmark;
import models.BenchmarkResult;
import util.BenchmarkUtil;

public class ProtoBenchmark extends BaseBenchmark {
    @Override
    public void runBenchmark() throws InvalidProtocolBufferException {
        SampleDataProto.SampleData sample = SampleDataProto.SampleData.newBuilder()
                .setName("Test")
                .setId(123)
                .setActive(true)
                .build();

        long startTime = System.nanoTime();
        byte[] protobufData = new byte[0];
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            protobufData = sample.toByteArray();
        }
        long serializationTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            SampleDataProto.SampleData.parseFrom(protobufData);
        }
        long deserializationTime = System.nanoTime() - startTime;

        int dataSize = protobufData.length;
        SerializationBenchmark.results.put("Protobuf", new BenchmarkResult(serializationTime, deserializationTime, dataSize));
        BenchmarkUtil.printResults("Protobuf", serializationTime, deserializationTime, dataSize);
    }
}
