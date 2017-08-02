package pl.edu.agh.idziak;

import com.google.common.base.Throwables;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;

/**
 * Created by Tomasz on 15.03.2017.
 */
public class MicrobenchmarkRunner {

    public static void runTestClasses(RunBuilder runBuilder) {
        ChainedOptionsBuilder options = new OptionsBuilder()
                .include(benchmarkClass.getName())
                .shouldFailOnError(true)
                .warmupIterations(1)
                .forks(debugMode ? 0 : 1)
                .threads(1)
                .measurementIterations(1)
                .operationsPerInvocation(5);
        //.jvmArgsAppend("-Djmh.stack.lines=5 -Djmh.stack.top=20");
        if (profileStack) {
            options.addProfiler(StackProfiler.class);
        }

        try {
            new Runner(options.build()).run();
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    public static class RunBuilder {
        private Class<?> benchmarkClass;
        private boolean debugMode;
        private Map<String, String> args;

        public RunBuilder benchmarkClass(Class<?> benchmarkClass) {
            this.benchmarkClass = benchmarkClass;
            return this;
        }

        public RunBuilder debugMode(boolean debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        public RunBuilder args(Map<String, String> args) {
            this.args = args;
            return this;
        }

        public RunBuilder run(){

        }
    }
}
