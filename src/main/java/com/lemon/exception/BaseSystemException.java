package com.lemon.exception;

/**
 * Created by simpletour_Jenkin on 2017/1/6.
 */
public class BaseSystemException extends RuntimeException{
    private static final long serialVersionUID = -1083172506547838758L;
    private IError error = DefaultError.SYSTEM_INTERNAL_ERROR;
    //存在在IError中没定义的异常信息，比如批量提交数据，其中一条数据出错了，报的异常信息放在extMessage中。
    private String extMessage = null;

    public BaseSystemException() {
    }

    public BaseSystemException(String message) {
        super(message);
        this.extMessage = message;
    }

    public BaseSystemException(String message, Throwable cause) {
        super(message, cause);
        this.extMessage = message;
    }

    public BaseSystemException(Throwable cause) {
        super(cause);
    }

    public BaseSystemException(IError error) {
        this.error = error;
    }

    public BaseSystemException(String extMessage, IError error) {
        this.extMessage = extMessage;
        this.error = error;
    }

    public IError getError() {
        return error;
    }

    public String getExtMessage() {
        return extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public String getMessage() {
        return error.getErrorMessage();
    }

    public String toString() {
        return super.toString() + ",ErrorCode : " + error.getErrorCode() + ", ErrorMessage : " + error.getErrorMessage();
    }
}
