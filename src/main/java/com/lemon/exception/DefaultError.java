package com.lemon.exception;

/**
 * Created by simpletour_Jenkin on 2017/1/6.
 */
public enum DefaultError implements IError{
    SYSTEM_INTERNAL_ERROR("0000", "System Internal Error"),
    INVALID_PARAMETER("0001", "Invalid Parameter"),
    TOKEN_NOT_FOUND("0002", "Token Not Found"),
    SERVICE_NOT_FOUND("0003", "Service Not Found"),
    INVALID_TOKEN("0004", "Invalid token"),
    CREATE_TOKEN_FAILED("0005", "Create token failed"),
    PARAMETER_REQUIRED("0006", "Parameter required"),
    PARAMETER_MAX_LENGTH("0007", "Parameter max length limit"),
    PARAMETER_MIN_LENGTH("0008", "Parameter min length limit"),
    PARAMETER_ANNOTATION_NOT_MATCH("0009", "Parameter annotation not match"),
    PARAMETER_NOT_MATCH_RULE("0010", "Parameter not match validation rule"),
    TABLE_OR_ENTITY_ANNOTATION_NOT_FOUND("0011", "Table or entity annotation not found"),
    SOME_ENTITY_DEPEND_ON_THE_DELETING_DOMAIN("0012", "存在某个对象依赖于当前正在删除的对象");

    String errorCode;
    String errorMessage;
    private static final String ns = "SYS";

    DefaultError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getNamespace() {
        return ns;
    }

    public String getErrorCode() {
        return ns + "." + errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
