package pl.edu.agh.idziak.other;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import pl.edu.agh.idziak.MicrobenchmarkRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@State(Scope.Benchmark)
public class FillingCollectionsBenchmarks {

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(this.getClass(), false, false);
    }

    private final int SIZE = 1000000;
    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> arrayList;
    private ImmutableMap<Integer, Integer> immutableMap;
    private int[] array;
    private LinkedList<Integer> linkedList;

    @Setup
    public void setup() {

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void hashMapFilling() throws Exception {
        map = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            map.put(i, i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayListFilling() throws Exception {
        arrayList = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void linkedListFilling() throws Exception {
        linkedList = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            linkedList.add(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void arrayFilling() throws Exception {
        array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = i;
        }
    }
}
