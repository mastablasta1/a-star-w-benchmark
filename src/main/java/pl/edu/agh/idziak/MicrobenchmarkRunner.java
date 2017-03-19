package pl.edu.agh.idziak;

import com.google.common.base.Throwables;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Tomasz on 15.03.2017.
 */
public class MicrobenchmarkRunner {

    public static void runTestClasses(Class benchmarkClass, boolean profileStack, boolean debugMode) {
        ChainedOptionsBuilder opt = new OptionsBuilder()
                .include(benchmarkClass.getName())
                .shouldFailOnError(true)
                .warmupIterations(1)
                .forks(debugMode ? 0 : 1)
                .threads(1)
                .measurementIterations(1)
                .operationsPerInvocation(5);
                //.jvmArgsAppend("-Djmh.stack.lines=5 -Djmh.stack.top=20");
        if (profileStack) {
            opt.addProfiler(StackProfiler.class);
        }

        try {
            new Runner(opt.build()).run();
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
