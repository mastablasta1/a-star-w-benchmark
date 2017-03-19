package pl.edu.agh.idziak;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

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
    private ImmutableList<Integer> immutableList;

    @Setup
    public void setup() {
        ImmutableMap.Builder<Integer, Integer> builder = ImmutableMap.builder();
        ImmutableList.Builder<Integer> builder1 = ImmutableList.builder();
        array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            map.put(i, i);
            arrayList.add(i);
            builder.put(i, i);
            array[i] = i;
            builder1.add(i);
        }
        immutableMap = builder.build();
        immutableList = builder1.build();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void hashMapIterationBenchmark(Blackhole blackhole) throws Exception {
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            blackhole.consume(iterator.next());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayListIterationBenchmark(Blackhole blackhole) throws Exception {
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            blackhole.consume(iterator.next());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayListForIndexBenchmark(Blackhole blackhole) throws Exception {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            blackhole.consume(arrayList.get(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void immutableMapBenchmark(Blackhole blackhole) throws Exception {
        UnmodifiableIterator<Map.Entry<Integer, Integer>> iterator = immutableMap.entrySet().iterator();
        while (iterator.hasNext()) {
            blackhole.consume(iterator.next());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayBenchmark(Blackhole blackhole) throws Exception {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            blackhole.consume(array[i]);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void immutableListBenchmark(Blackhole blackhole) throws Exception {
        int size = immutableList.size();
        for (int i = 0; i < size; i++) {
            blackhole.consume(immutableList.get(i));
        }
    }

    private int ignore(int i) {
        return i;
    }
}
