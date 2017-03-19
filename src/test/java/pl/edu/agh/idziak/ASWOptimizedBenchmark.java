package pl.edu.agh.idziak;

import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import pl.edu.agh.idziak.asw.impl.AlgorithmType;
import pl.edu.agh.idziak.asw.utils.test.grid2d.model.OptimizedTestCase;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomasz on 15.03.2017.
 */
@State(Scope.Benchmark)
@Ignore
public class ASWOptimizedBenchmark {

    private static final String ONE_ENTITY_7X7 = "7x7 - 1 entity";
    private static final String SEVEN_ENTITIES_7X7 = "7x7 - 7 entities";
    private static final String FIVE_ENTITIES_7X7 = "7x7 - 5 entities";
    private static final String FOUR_ENTITIES_7X7 = "7x7 - 4 entities";
    private static final String FOUR_ENTITIES_5X5 = "5x5 - 4 entities";
    private static final String CONFINED_SWAP_2_ENTITIES_7x2 = "confined swap - 2 entities - 7x2";
    private static final String TWO_ENTITIES_7X7 = "7x7 - 2 entities";
    private static final String SWAP_OVERSTEPPING_4_ENTITIES = "swap (with overstepping) - 4 entities - 7x3";
    private static final String SIX_ENTITIES_7X7 = "7x7 - 6 entities";
    private static final String THREE_ENTITIES_7X7 = "7x7 - 3 entities";

    @Test
    public void runBenchmarks() throws Exception {
        MicrobenchmarkRunner.runTestClasses(this.getClass(), false, true);
    }

    private OptimizedASWTestSuite testSuite;

    @Setup
    public void setup() throws IOException {
        testSuite = new OptimizedASWTestSuite(Paths.get("test1.json"));
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
        OptimizedTestCase optimizedTestCase = testSuite.executeTest(FOUR_ENTITIES_7X7, AlgorithmType.ASTAR_ONLY);
        System.out.println(optimizedTestCase.getExtendedOutputPlan().getOutputPlan().getCollectivePath());
        System.out.println(optimizedTestCase.getExtendedOutputPlan().getBenchmark());
    }
}