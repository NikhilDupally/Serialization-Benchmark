package models;

public class BenchmarkResult {
    long serializationTime;
    long deserializationTime;
    int dataSize;
    public BenchmarkResult(long serializationTime, long deserializationTime, int dataSize) {
        this.serializationTime = serializationTime;
        this.deserializationTime = deserializationTime;
        this.dataSize = dataSize;
    }

    public int getDataSize() {
        return dataSize;
    }

    public long getDeserializationTime() {
        return deserializationTime;
    }

    public long getSerializationTime() {
        return serializationTime;
    }
}
