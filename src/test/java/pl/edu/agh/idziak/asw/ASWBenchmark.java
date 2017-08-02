package pl.edu.agh.idziak.asw;

import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import pl.edu.agh.idziak.ASWTestSuite;
import pl.edu.agh.idziak.MicrobenchmarkRunner;
import pl.edu.agh.idziak.asw.impl.AlgorithmType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomasz on 15.03.2017.
 */
@State(Scope.Benchmark)
@Ignore
public class ASWBenchmark {

    private final String TWO_ENTITIES_7X7 = "7x7 - 2 entities";
    private final String SWAP_OVERSTEPPING_4_ENTITIES = "swap (with overstepping) - 4 entities - 7x3";

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(ASWBenchmark.class, false, false);
    }

    private ASWTestSuite testSuite;

    @Setup
    public void setup() throws IOException {
        testSuite = new ASWTestSuite(Paths.get("test1.json"));
    }

//    @Benchmark
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmarkWavefront() {
        testSuite.executeTest(SWAP_OVERSTEPPING_4_ENTITIES, AlgorithmType.WAVEFRONT);
        System.out.println("done");
    }

//    @Benchmark
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmarkASW() {
        testSuite.executeTest(SWAP_OVERSTEPPING_4_ENTITIES, AlgorithmType.ASW);
    }

    @Benchmark
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmarkAStar() {
        testSuite.executeTest(SWAP_OVERSTEPPING_4_ENTITIES, AlgorithmType.ASTAR_ONLY);
    }
}