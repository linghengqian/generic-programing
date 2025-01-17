package sukai.currencyadvance.chapter01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chengsukai
 * @since 2022-08-30 15:38
 */
@Measurement(iterations = 20)
@Warmup(iterations = 20)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class AtomicReferenceExample {

    @State(Scope.Group)
    public static class MonitorRace {
        private DebitCard debitCard = new DebitCard("Alex", 0);

        public void syncInc() {
            synchronized (AtomicReferenceExample2.class) {
                final DebitCard dc = debitCard;
                final DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                this.debitCard = newDC;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicReferenceRate {
        private AtomicReference<DebitCard> ref = new AtomicReference<>(new DebitCard("Alex", 0));

        public void casInc() {
            final DebitCard dc = ref.get();
            final DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
            ref.compareAndSet(dc, newDC);
        }
    }

    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void syncInc(MonitorRace monitor) {
        monitor.syncInc();
    }


    @GroupThreads(10)
    @Group("cas")
    @Benchmark
    public void casInc(AtomicReferenceRate casRace) {
        casRace.casInc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder().include(AtomicReferenceExample.class.getSimpleName()).forks(1).timeout(TimeValue.seconds(10)).addProfiler(StackProfiler.class).build();
        new Runner(opts).run();
    }


}
