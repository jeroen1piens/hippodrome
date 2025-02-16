import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value =22)
    public void mainTest() {
        String[] args = new String[1];
        try {
            Main.main(args);
        } catch (Exception e) {
            throw (new RuntimeException(e));
        }
    }
}
