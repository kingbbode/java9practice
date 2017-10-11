package com.kingbbode.java9practice.java.util;

import javafx.util.Pair;
import org.junit.After;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 28..
 */
public class MapTest {

    private Map<String, String> map;

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

        /*
        
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
        map.put("key7", "value7");
        map.put("key8", "value8");
        map.put("key9", "value9");
        map.put("key10", "value10");
        map.put("key11", "value11");
        map.put("key12", "value12");
        map.put("key13", "value13");
        map.put("key14", "value14");
        Collections.unmodifiableMap(map);
        
        Stream.of(
                new Pair<>("key1", "value1"),
                new Pair<>("key2", "value2"),
                new Pair<>("key3", "value3"),
                new Pair<>("key4", "value4"),
                new Pair<>("key5", "value5"),
                new Pair<>("key6", "value6"),
                new Pair<>("key7", "value7"),
                new Pair<>("key8", "value8"),
                new Pair<>("key9", "value9"),
                new Pair<>("key10", "value10"),
                new Pair<>("key11", "value11"),
                new Pair<>("key12", "value12"),
                new Pair<>("key13", "value13"),
                new Pair<>("key14", "value14")
            ).collect(collectingAndThen(toMap(Pair::getKey, Pair::getValue),Collections::unmodifiableMap));
        */
    }

    @Test
    public void toEntries에_가변인자를_넘길_수_있으니_스트림을_이용해서() throws Exception {
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

    @Test(expected = IllegalArgumentException.class)
    public void 키_중복을_허용하지_않는다() throws Exception {
        Map.of(
                "key1", "value1",
                "key2", "value1",
                "key3", "value1",
                "key1", "value1"
        );
    }

    @After
    public void tearDown() throws Exception {
        if(map == null){
            return;
        }
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
