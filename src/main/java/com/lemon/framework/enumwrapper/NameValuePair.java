package com.lemon.framework.enumwrapper;

/**
 * Created by igotti on 15-1-18.
 */
public class NameValuePair<V> {

    private String name;

    private V value;

    public NameValuePair() {}

    public NameValuePair(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
