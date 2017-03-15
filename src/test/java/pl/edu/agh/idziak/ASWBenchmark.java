package pl.edu.agh.idziak;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import pl.edu.agh.idziak.asw.impl.AlgorithmType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomasz on 15.03.2017.
 */
@State(Scope.Benchmark)
public class ASWBenchmark {

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(ASWBenchmark.class);
    }

    private ASWTestSuite testSuite;

    @Setup
    public void setup() throws IOException {
        testSuite = new ASWTestSuite(Paths.get("test1.json"));
    }

    @Benchmark
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmarkWavefront() {
        testSuite.executeTest("swap (with overstepping) - 4 entities - 7x3", AlgorithmType.WAVEFRONT);
    }

    @Benchmark
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmarkASW() {
        testSuite.executeTest("swap (with overstepping) - 4 entities - 7x3", AlgorithmType.ASW);
    }
}