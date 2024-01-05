package com.example.myproject.DAO.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LambdaTest {


    @Test
    public void lambdaTest() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < 100; i++) {
            list.add(i);
        }
        list.stream().filter(n -> n == 10).forEach(n -> log.info("n : {}", n));
//        Consumer<Integer> method =  (n) -> {log.info("n = {}", n);};
//        list.forEach(method);
    }

    @Test
    public void lambdaTest2() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < 100; i++) {
            list.add(i);
        }
        list.forEach(n -> log.info("n : {}", n));
    }

    @Test
    public void doubleColonTest() {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "b", "broken", "cat"));
        // forEach method
//        for(String str : list) {
//            if(str.equals("b")) {
//                log.info(str);
//            }
//        }
        // lambda method
//        list.stream().filter((x) -> x.equals("b")).forEach(log::info);
        // double colon method
        list.stream().filter("b"::equals).forEach(log::info);
    }

    @Test
    public void IntFunctionTest() {
        IntFunction<Integer> intDivide = x -> x / 2;
        log.info("divide by 2 : {}", intDivide.apply(2));
    }

    @Test
    public void streamTest() {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            list.add(i);
        }
        long count = list.stream().filter(x -> x % 6 == 0).count();
        long max = list.stream().max(Comparator.comparing(x -> x % 6 == 0)).orElseThrow();
        log.info("1 ~ 100 중 6의 배수는 {}개입니다.", count);
        log.info("1 ~ 100 중 6의 배수의 최댓값은 {}입니다.", count);
    }

    @Test
    public void streamTest2() throws Exception {
        String[] lines = {"hello", "helloWorld", "helloWorld22"};
        String maxString = Arrays.stream(lines).max(Comparator.comparing(String::length)).orElseThrow();
        int maxLength = maxString.length();
        log.info("maxString: {}", maxString);
        log.info("maxLength: {}", maxLength);
    }

    @Test
    public void streamTest3() throws Exception {
        String[] lines = {"hello", "helloWorld", "helloWorld22"};
        int mxLen = 0;
        String mxStr = "";
        for(String str : lines) {
            int curLen = 0;
            curLen = str.length();
            if (mxLen < curLen) {
                mxLen = curLen;
                mxStr = str;
            }
        }
        log.info("maxString: {}", mxStr);
        log.info("maxLength: {}", mxLen);
    }

    @FunctionalInterface
    interface Calculator {
        public int calculate(int x, int y);
    }

    @Test
    public void a() throws Exception {
//        Calculator plus = (x, y) -> x + y;
        Calculator add = Integer::sum;
        Calculator subtract = (x, y) -> x - y;

        log.info("plus : {}", add.calculate(2,142));
        log.info("subtract : {}", subtract.calculate(2,142));
    }

}
