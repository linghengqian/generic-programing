package sukai.currency.caseVI;

import java.util.concurrent.CompletableFuture;

/**
 * @author chengsukai
 * @since 2022-08-15 10:17
 */
public class _01_thenApply {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白 结账(要求开发票)");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款 500元");
            SmallTool.sleepMills(100);
            SmallTool.printTimeAndThread("服务员开发票 面额 500元");
            return "500元发票";
        });

        SmallTool.printTimeAndThread("小白 接到朋友的电话 想一起打游戏");

        SmallTool.printTimeAndThread(String.format("小白拿到%s, 准备回家", invoice.join()));
    }
}
