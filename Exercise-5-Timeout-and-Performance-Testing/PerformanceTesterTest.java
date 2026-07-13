import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class PerformanceTesterTest {

    @Test
    void testPerformance() {

        PerformanceTester performanceTester = new PerformanceTester();

        assertTimeout(Duration.ofSeconds(2), () -> {
            performanceTester.performTask();
        });

    }
}
