package other;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Measurement(iterations = 10, batchSize = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkLoop {

    private static long inputValue = 33761L;
    //33 761 - ~75 sec for processing
    //33 762 - ~1.5 sec for processing

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)

    public void process() {
        StringBuilder key = new StringBuilder("Lapushkin");
        double module = Math.pow(2, 64);
        long hash;
        long salt = 0;
        long start = System.currentTimeMillis();
        do {
            key.insert(0, salt);
            hash = (long) ((key.toString()).hashCode() % module);
            salt++;
        } while (Math.abs(hash) >= inputValue);

        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("Прошло времени, мс: " + total);
//        System.out.println(salt);
//        System.out.println(key);
    }
}
