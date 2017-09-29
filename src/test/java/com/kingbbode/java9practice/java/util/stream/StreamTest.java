package com.kingbbode.java9practice.java.util.stream;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 29..
 */
public class StreamTest {

    private List<Integer> list;

    @Test
    public void 조건까지의_데이터를_스트림으로_만드는_takeWhile() throws Exception {
        list = Stream.of(
                IntStream.range(1, 100)
                        .boxed()
                        .toArray(Integer[]::new)
        ).takeWhile(integer -> integer < 50)
                .collect(Collectors.toList());
        assertThat(list.size(), is(49));
    }

    @Test
    public void takeWhile은_전체를_조건으로_filter하는_것이_아님() throws Exception {
        list = Stream.of(
                1,2,3,4,5,1,2,3,4,5
        ).takeWhile(integer -> integer < 5)
                .collect(Collectors.toList());
        assertThat(list.size(), is(4));
    }

    @Test
    public void 조건까지의_데이터를_버리고_스트림으로_만드는_dropWhile() throws Exception {
        list = Stream.of(
                IntStream.range(1, 100)
                        .boxed()
                        .toArray(Integer[]::new)
        ).dropWhile(integer -> integer < 50)
                .collect(Collectors.toList());
        assertThat(list.size(), is(50));
    }

    @Test
    public void dropWhile은_전체를_조건으로_filter하는_것이_아님() throws Exception {
        list = Stream.of(
                1,2,3,4,5,1,2,3,4,5
        ).dropWhile(integer -> integer < 5)
                .collect(Collectors.toList());
        assertThat(list.size(), is(6));
    }

    @Test
    public void for문의_Stream_버전_iterate() throws Exception {
        list = Stream.iterate(1, x-> x < 11, x->x+3).collect(Collectors.toList());
        assertThat(list.size(), is(4));
    }

    @Test
    public void iterate를_활용한_리스트의_특정_조건을_인덱스_값_맵으로_변환() throws Exception {
        List<String> stringList = List.of("OK1","NO2", "OK2", "NO2", "OK3");
        Map<Integer, String> map = Stream.iterate(0, x-> x < stringList.size(), x->x+1)
                .filter(index -> !stringList.get(index).startsWith("NO"))
                .collect(Collectors.toMap(index -> index, stringList::get));
        map.forEach((key, value) -> {
            assertThat(key % 2 == 0, is(true));
            assertThat(value.startsWith("OK"), is(true));
            System.out.println("key : " + key + ",value : " + value);
        });
        list = new ArrayList<>(map.keySet());
    }

    @After
    public void tearDown() throws Exception {
        list.forEach(System.out::println);
    }
}
