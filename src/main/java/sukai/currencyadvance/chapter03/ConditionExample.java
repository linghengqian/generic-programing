package sukai.currencyadvance.chapter03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author chengsukai
 * @since 2022-09-01 12:16
 */
public class ConditionExample {

    //定义共享数据
    private static int shareData = 0;

    private static boolean dataUsed = false;

    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();


    private static void change() {
        lock.lock();

        try {
            while (!dataUsed) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(current().nextInt(5));
            shareData++;
            dataUsed = false;
            System.out.println("produce the new value: " + shareData);
            condition.signal(); //提醒wait队列的其他线程你--数据使用线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void use() {
        lock.lock();
        try {
            while (dataUsed) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(current().nextInt(5));
            dataUsed = true;
            System.out.println("the shared data changed: " + shareData);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(
                () -> {
                    for (; ; ) {
                        change();
                    }
                }, "Producer"
        ).start();

        new Thread(() -> {
            for (; ; ) {
                use();
            }
        }, "Consumer").start();
    }


}
