package com.kingbbode.java9practice.interfaces;

/**
 * Created by YG-MAC on 2017. 10. 1..
 */
public interface TestInterface {

    default String publicMethod() {
        return this.privateInterfaceMethod();
    }

    private String privateInterfaceMethod() {
        return "From Interface Private Method";
    }
}
