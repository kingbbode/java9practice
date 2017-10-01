package com.kingbbode.java9practice.java.interfaces;

import com.kingbbode.java9practice.interfaces.TestInterface;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by YG-MAC on 2017. 10. 1..
 */
public class PrivateInterfaceTest {

    private class TestClass implements TestInterface {
    }

    private class TestClass2 implements TestInterface {
        private String privateInterfaceMethod() {
            return "From Class Private Method";
        }
    }

    @Test
    public void 인터페이스_프리아빗_메소드는_상속받은_클래스에서_접근할_수_없다() throws Exception {
        TestClass test = new TestClass();
        assertThat(test.publicMethod(), is("From Interface Private Method"));
        assertThat(Arrays.stream(test.getClass().getDeclaredMethods()).anyMatch(method -> "privateInterfaceMethod".equals(method.getName())), is(false));
    }

    @Test
    public void 인터페이스_프라이빗_메소드는_재정의할_수_없다() throws Exception {
        TestClass2 test2 = new TestClass2();
        assertThat(test2.publicMethod(), is("From Interface Private Method"));
    }
}
