package benchmark;

import com.google.flatbuffers.FlatBufferBuilder;
import core.BaseBenchmark;
import models.BenchmarkResult;
import util.BenchmarkUtil;
import java.nio.ByteBuffer;

public
class FlatBufferBenchmark extends BaseBenchmark {
    @Override
    public void runBenchmark() {
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);
        long startTime = System.nanoTime();
        int offset = 0;
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            builder.clear();
            offset = sampledata.SampleDataFlatBuffer.createSampleDataFlatBuffer(builder,
                    builder.createString("Test"), 123, true);
            builder.finish(offset);
        }
        long serializationTime = System.nanoTime() - startTime;

        ByteBuffer buffer = builder.dataBuffer();
        startTime = System.nanoTime();
        for (int i = 0; i < SerializationBenchmark.ITERATIONS; i++) {
            sampledata.SampleDataFlatBuffer.getRootAsSampleDataFlatBuffer(buffer);
        }
        long deserializationTime = System.nanoTime() - startTime;

        int dataSize = buffer.remaining();
        SerializationBenchmark.results.put("FlatBuffers", new BenchmarkResult(serializationTime, deserializationTime, dataSize));
        BenchmarkUtil.printResults("FlatBuffers", serializationTime, deserializationTime, dataSize);
    }
}