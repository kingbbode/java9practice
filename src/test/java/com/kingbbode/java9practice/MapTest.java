package com.kingbbode.java9practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 28..
 */
public class MapTest {

    private Map<String, String> map;

    @Before
    public void setUp() throws Exception {
        System.out.println("구아바없이 맵을 한번에 하다니!! JAVA 9 !!");
    }

    @Test
    public void of는_최대_10개(){
        this.map =
                Map.of(
                        "key1","value1",
                        "key2","value2",
                        "key3","value3",
                        "key4","value4",
                        "key5","value5",
                        "key6","value6",
                        "key7","value7",
                        "key8","value8",
                        "key9","value9",
                        "key10","value10"
                );
        System.out.println("Map.of는 최대 10개");
        assertThat(this.map.size(), is(10));
    }

    @Test
    public void toEntries는_쭉쭉_들어간다() throws Exception {
        this.map =
                Map.ofEntries(
                        Map.entry("key1", "value1"),
                        Map.entry("key2", "value2"),
                        Map.entry("key3", "value3"),
                        Map.entry("key4", "value4"),
                        Map.entry("key5", "value5"),
                        Map.entry("key6", "value6"),
                        Map.entry("key7", "value7"),
                        Map.entry("key8", "value8"),
                        Map.entry("key9", "value9"),
                        Map.entry("key10", "value10"),
                        Map.entry("key11", "value11"),
                        Map.entry("key12", "value12"),
                        Map.entry("key13", "value13"),
                        Map.entry("key14", "value14")
                );
        assertThat(this.map.size(), is(14));
    }

    @Test
    public void toEntries에_배열넘겨보기() throws Exception {
        map =
                Map.ofEntries(
                        IntStream.range(1, 100)
                                .mapToObj(index ->
                                        Map.entry("key" + index, "value" + index)
                                )
                                .toArray((IntFunction<Map.Entry<String, String>[]>) Map.Entry[]::new)
                );
        assertThat(map.size(), is(99));

    }

    @After
    public void tearDown() throws Exception {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
