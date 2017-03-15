package pl.edu.agh.idziak;

import com.google.common.base.Throwables;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Tomasz on 15.03.2017.
 */
public class MicrobenchmarkRunner {

    public static void runTestClasses(Class benchmarkClass) {
        Options opt = new OptionsBuilder()
                .include(benchmarkClass.getName())
                .shouldFailOnError(true)
                .warmupIterations(1)
                .forks(1)
                .threads(1)
                .measurementIterations(1)
                .operationsPerInvocation(10)
                // .addProfiler(HotspotMemoryProfiler.class)
                .build();

        try {
            new Runner(opt).run();
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
