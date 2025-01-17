package sukai.currencyadvance.chapter06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;

/**
 * @author chengsukai
 * @since 2022-09-05 08:56
 */
public class CompletetableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync:" + currentThread());
            return "java";
            // 以同步的方式处理上一个异步任务的结果
        }, executor).thenApplyAsync(e -> {
            System.out.println("thenApply:" + currentThread());
            return e.length();
        }).thenRun(()->{
            System.out.println("All of task completed. " + currentThread());
        });
    }
}
