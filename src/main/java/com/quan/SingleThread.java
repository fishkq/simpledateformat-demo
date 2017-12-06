package com.quan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yu on 06/12/2017 10:10 AM.
 */
public class SingleThread {
    private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) throws ParseException {
        SingleThread singleThread = new SingleThread();
        singleThread.parse();
    }

    private void parse() throws ParseException {
        Date date = df.parse("20171206105413");
        System.out.println(date);
    }
}
