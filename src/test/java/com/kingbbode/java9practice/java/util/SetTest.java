package com.kingbbode.java9practice.java.util;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 29..
 */
public class SetTest {

    private Set<Integer> set;

    @Test
    public void of에_쭉쭉쭉() throws Exception {
        set = Set.of(
                1,2,3,4,5,6,7,8,9,10,11
        );

        /*
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11)));
            Stream.of(1,2,3,4,5,6,7,8,9,10,11).collect(collectingAndThen(toSet(),Collections::unmodifiableSet));
         */
        assertThat(set.size(), is(11));
    }

    @Test
    public void of에_가변인자도_넘길_수_있으니_스트림을_이용해서() throws Exception {
        set = Set.of(
                IntStream.range(1, 100)
                        .boxed()
                        .toArray(Integer[]::new)
        );
        /*
            IntStream.range(1, 100).boxed().collect(collectingAndThen(toSet(),Collections::unmodifiableSet));
        */
        assertThat(set.size(), is(99));
    }

    @After
    public void tearDown() throws Exception {
        set.forEach(System.out::println);
    }
}
