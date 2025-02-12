package jmhbenchmark;

import com.example.protobuf.SampleDataProto;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ProtoBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(ProtoBenchmark.class);

    private SampleDataProto.SampleData sample;
    private byte[] protobufData;

    @Setup(Level.Invocation)
    public void setup() {
        sample = SampleDataProto.SampleData.newBuilder()
                .setName("Test")
                .setId(123)
                .setActive(true)
                .build();
        protobufData = sample.toByteArray();
    }

    @Benchmark
    public byte[] serialize() {
        return sample.toByteArray();
    }

    @Benchmark
    public SampleDataProto.SampleData deserialize() throws Exception {
        return SampleDataProto.SampleData.parseFrom(protobufData);
    }
}

