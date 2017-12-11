package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShannonAJ on 2017/12/11.
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
