package jmhbenchmark;

import com.google.flatbuffers.FlatBufferBuilder;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class FlatBufferBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(FlatBufferBenchmark.class);

    private FlatBufferBuilder builder;
    private ByteBuffer flatBufferData;
    private int offset;

    @Setup(Level.Invocation)
    public void setup() {
        builder = new FlatBufferBuilder(1024);
        offset = sampledata.SampleDataFlatBuffer.createSampleDataFlatBuffer(builder, builder.createString("Test"), 123, true);
        builder.finish(offset);
        flatBufferData = builder.dataBuffer();
    }

    @Benchmark
    public ByteBuffer serialize() {
        builder.clear();
        offset = sampledata.SampleDataFlatBuffer.createSampleDataFlatBuffer(builder, builder.createString("Test"), 123, true);
        builder.finish(offset);
        return builder.dataBuffer();
    }

    @Benchmark
    public sampledata.SampleDataFlatBuffer deserialize() {
        return sampledata.SampleDataFlatBuffer.getRootAsSampleDataFlatBuffer(flatBufferData);
    }
}

