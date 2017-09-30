package com.kingbbode.java9practice.java.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 9. 30..
 */
public class OptionalTest {
    private List<Optional<Integer>> optionalList;
    private List<Integer> result;

    @Before
    public void setUp() throws Exception {
        optionalList = List.of(
                Optional.empty(),
                Optional.of(2),
                Optional.empty(),
                Optional.of(4),
                Optional.empty(),
                Optional.of(6),
                Optional.empty(),
                Optional.of(8),
                Optional.empty(),
                Optional.of(10)
        );
    }

    @Test
    public void Stream_객체로_변신하는_stream() throws Exception {
        result = optionalList.stream().flatMap(Optional::stream).collect(Collectors.toList());
        assertThat(result.size(), is(5));
        result.forEach(integer -> assertThat(integer % 2, is(0)));
    }

    @Test
    public void ifPresent를_확장하여_else_조건까지_한번에_ifPresentOrElse() throws Exception {
        result = new ArrayList<>();
        IntStream.iterate(1, x -> x <= optionalList.size(), x -> x + 1)
                .forEach(x -> optionalList.get(x-1).ifPresentOrElse(
                    integer -> result.add(integer),
                    () -> result.add(x)
                ));
        assertThat(result.size(), is(10));
        IntStream.range(1, 11).forEach(value -> assertThat(result.contains(value), is(true)));
    }

    @Test
    public void 값이_존재하지않으면_다른_Optional을_반환할_수_있는_or() throws Exception {
        Optional<Integer> optional = findBoxA() //empty
                                        .or(this::findBoxB) //10
                                        .or(this::findBoxC); //20
        
        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), is(10));
        result = List.of();
    }

    private Optional<Integer> findBoxA() {
        return Optional.empty();
    }

    private Optional<Integer> findBoxB() {
        return Optional.of(10);
    }

    private Optional<Integer> findBoxC() {
        return Optional.of(20);
    }

    @After
    public void tearDown() throws Exception {
        result.forEach(System.out::println);
    }
}
