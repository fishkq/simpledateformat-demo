package com.quan;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yu on 06/12/2017 2:23 PM.
 */
public class JodaTime {
    private static final DateTimeFormatter df = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public static void main(String[] args) throws ParseException, ExecutionException, InterruptedException {
        JodaTime jodaTime = new JodaTime();
        jodaTime.run();
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
        DateTime date = df.parseDateTime("20171206105413");
        return date.toDate();
    }
}
