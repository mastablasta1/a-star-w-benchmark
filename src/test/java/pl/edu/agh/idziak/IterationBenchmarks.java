package pl.edu.agh.idziak;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@State(Scope.Benchmark)
@Ignore
public class IterationBenchmarks {

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(this.getClass(), false, false);
    }

    private final int SIZE = 1000000;
    private HashMap<Integer, Integer> map = new HashMap<>(SIZE);
    private ArrayList<Integer> arrayList = new ArrayList<>(SIZE);
    private ImmutableMap<Integer, Integer> immutableMap;
    private int[] array;

    @Setup
    public void setup() {
        ImmutableMap.Builder<Integer, Integer> builder = ImmutableMap.builder();
        array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            map.put(i, i);
            arrayList.add(i);
            builder.put(i, i);
            array[i] = i;
        }
        immutableMap = builder.build();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void hashMapIterationBenchmark() throws Exception {
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayListIterationBenchmark() throws Exception {
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayListForIndexBenchmark() throws Exception {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void immutableMapBenchmark() throws Exception {
        UnmodifiableIterator<Map.Entry<Integer, Integer>> iterator = immutableMap.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayBenchmark() throws Exception {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            ignore(array[i]);
        }
    }

    private int ignore(int i) {
        return i;
    }
}
