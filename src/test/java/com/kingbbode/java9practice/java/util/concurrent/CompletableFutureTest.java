package com.kingbbode.java9practice.java.util.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 30..
 */
public class CompletableFutureTest
{
    private CompletableFuture<String> future;
    private long start;
    private String result = "FAILED";

    @Before
    public void setUp() throws Exception {
        future = CompletableFuture.
                supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "king";
                })
                .thenApply(s -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s + "bbode";
                })
                .whenComplete((s, throwable) -> {
                    if(throwable != null) {
                        System.out.println("ERROR : " + throwable.getMessage());
                    }
                });
        start = System.currentTimeMillis();
    }

    @Test
    public void 일단_정상_케이스() throws ExecutionException, InterruptedException {
        result = future.get();
        assertThat(result, is("kingbbode"));
        assertThat(((System.currentTimeMillis() - start) / 1000) < 3, is(true));

    }

    @Test
    public void 시간_지연하기_delay() throws Exception {
        future = CompletableFuture.
                supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "king";
                }, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS))
                .thenApply(s -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s + "bbode";
                })
                .whenComplete((s, throwable) -> {
                    if(throwable != null) {
                        System.out.println("ERROR : " + throwable.getMessage());
                    }
                });
        result = future.get();
        assertThat(((System.currentTimeMillis() - start) / 1000) > 4, is(true));
    }

    @Test(expected = ExecutionException.class)
    public void 시간제한_설정_orTimeout() throws Exception {
        future = future.orTimeout(1, TimeUnit.SECONDS);
        result = future.get();
    }

    @Test
    public void 타임아웃시_설정값_반환하게하는_completeOnTimeout() throws Exception {
        future = future.completeOnTimeout("킹뽀대", 1, TimeUnit.SECONDS);
        result = future.get();
        assertThat(result, is("킹뽀대"));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(result);
    }
}
