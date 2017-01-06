package com.lemon.exception;

/**
 * Created by simpletour_Jenkin on 2017/1/6.
 */
public interface IError {
    public String getNamespace();

    public String getErrorCode();

    public String getErrorMessage();
}
