package pl.edu.agh.idziak.other;

import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.edu.agh.idziak.MicrobenchmarkRunner;

import java.util.ArrayList;

@State(Scope.Benchmark)
@Ignore
public class IterationVsAllocationBenchmarks {

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(this.getClass(), false, false);
    }

    private final int SIZE = 1000000;
    private ArrayList<Boolean> arrayList = new ArrayList<>(SIZE);
    private boolean[] array = new boolean[SIZE];

    @Setup
    public void setup() {
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayAllocation(Blackhole blackhole) throws Exception {
        boolean[] obj = new boolean[SIZE];
        obj[10000] = true;
        blackhole.consume(obj);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void array2DimAllocation(Blackhole blackhole) throws Exception {
        boolean[][] obj = new boolean[1000][1000];
        blackhole.consume(obj);
    }

    // @Benchmark
    // @BenchmarkMode(Mode.Throughput)
    public void arrayListGetByIndexBenchmark(Blackhole blackhole) throws Exception {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            blackhole.consume(arrayList.set(i, Boolean.FALSE));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayBenchmark(Blackhole blackhole) throws Exception {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            blackhole.consume(array[i] = true);
        }
    }


}
