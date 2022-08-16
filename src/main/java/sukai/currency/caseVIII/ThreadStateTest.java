package sukai.currency.caseVIII;

import java.util.concurrent.TimeUnit;

/**
 * @author chengsukai
 * @since 2022-08-16 15:17
 */
public class ThreadStateTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println("1+ " + thread.getState());
        thread.start();
        System.out.println("2+ " + thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3+" + thread.getState());
    }
}
