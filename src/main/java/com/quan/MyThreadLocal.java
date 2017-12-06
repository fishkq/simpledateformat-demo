package com.quan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yu on 06/12/2017 2:14 PM.
 */
public class MyThreadLocal {
    private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static void main(String[] args) throws ParseException, ExecutionException, InterruptedException {
        MyThreadLocal myThreadLocal = new MyThreadLocal();
        myThreadLocal.run();
    }

    private void run() throws ParseException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Date> task = new Callable<Date>() {
            public Date call() throws Exception {
                return parse();
            }
        };

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        for (int i = 0; i < 5; i++) {
            results.add(executorService.submit(task));
        }

        executorService.shutdown();

        boolean flag = executorService.awaitTermination(1, TimeUnit.MINUTES);
        if (flag) {
            for (Future<Date> result : results) {
                System.out.println(result.get());
            }
        }
    }

    private Date parse() throws ParseException {
        Date date = df.get().parse("20171206105413");
        return date;
    }

}
