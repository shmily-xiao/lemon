package com.lemon.framework.enumwrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhouxin on 2015/12/16.
 * 枚举包装类，定义枚举的一些公共方法
 */
public class EnumWrapper<T extends Enum> {

    /**
     * 空实现
     */
    private static final EnumWrapper<?> EMPTY = new EnumWrapper<>();

    /**
     * 枚举类的class
     */
    private final Class<T> value;

    /**
     * 根据value获取枚举类
     * @param enumValue
     * @return
     */
    public T getEnumByValue(String enumValue){
        return getEnum(enumValue, item -> item.getValue());
    }

    /**
     * 根据name获取枚举类
     * @param enumName
     * @return
     */
    public T getEnumByName(String enumName){
        return getEnum(enumName, item -> item.getName());
    }

    /**
     * 根据参数和fun来获取枚举类
     * @param str
     * @param fun
     * @return
     */
    private T getEnum(String str, Function<Options, String> fun){
        return str == null?convertEnumToStream().findFirst().get():
                (T) convertEnumToStream().map(item -> (Options) item)
                                         .filter(item -> str.equals(fun.apply(item)))
                                         .findFirst().orElse((Options) convertEnumToStream().findFirst().get());
    }

    /**
     * 构造Option列表，该方法除了枚举值之外还可以构造一个额外的首个Option，默认这个Option的name为全部，value默认为空字符串，当前默认选中第一个
     *
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getDefaultOptionsList() {
        return getOptionsList("全部", "", null);
    }

    /**
     * 构造Option列表
     *
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getAllOptionsList(String queryValue) {
        List<Option> list = convertEnumToOptions(item -> item.getName(), item -> item.getValue());
        this.filterQueryValue(list, queryValue);
        return list;
    }

    /**
     * 构造Option列表
     *
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getAllOptionsList() {
        List<Option> list = convertEnumToOptions(item -> item.getName(), item -> item.getValue());
        this.filterQueryValue(list, null);
        return list;
    }

    /**
     * 构造Option列表，该方法除了枚举值之外还可以构造一个额外的首个Option，可以根据传入的条件初始化这个Option的name，value默认为空字符串，当前默认选中第一个
     *
     * @param firstName 首个Option的name
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getOptionsList(String firstName) {
        return getOptionsList(firstName, "", null);
    }

    /**
     * 构造Option列表，该方法除了枚举值之外还可以构造一个额外的首个Option，可以根据传入的条件初始化这个Option的name，value默认为空字符串，当前默认选中传入的值所对应的option
     *
     * @param firstName 首个Option的name
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getOptionsList(String firstName, String queryValue) {
        return getOptionsList(firstName, "", queryValue);
    }

    /**
     * 构造Option列表，该方法除了枚举值之外还可以构造一个额外的首个Option，可以根据传入的条件初始化这个Option的name和value
     *
     * @param firstName 首个Option的name
     * @param firstValue 首个Option的value
     * @param queryValue 首个列表查询时下拉框的value
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getOptionsList(String firstName, String firstValue, String queryValue) {
        List<Option> list = new ArrayList<>(Arrays.asList(new Option(firstName, firstValue)));
        if (value != null) list.addAll(convertEnumToOptions(item -> item.getName(), item -> item.getValue()));
        this.filterQueryValue(list, queryValue);
        return list;
    }

    private void filterQueryValue(List<Option> list, String queryValue){
        if (queryValue == null) {
            list.get(0).setSelected(true);
        } else{
            list.stream().filter(item -> item.getValue().equals(queryValue)).findFirst().ifPresent(item -> item.setSelected(true));
        }
    }

    /**
     * 构造Option列表，该方法只会根据枚举值构造Option列表
     *
     * @param <T> 枚举类型
     * @return
     */
    public <T extends Enum> List<Option> getOptionsListWithoutFirst() {
        List<Option> list = convertEnumToOptions(item -> item.getName(), item -> item.getValue());
        list.get(0).setSelected(true);
        return list;
    }

    private EnumWrapper() {
        this.value = null;
    }

    public static<T extends Enum> EnumWrapper<T> empty() {
        @SuppressWarnings("unchecked")
        EnumWrapper<T> t = (EnumWrapper<T>) EMPTY;
        return t;
    }

    private EnumWrapper(Class<T> value) {
        this.value = value.isEnum()?value:null;
    }

    /**
     * of的构造方法
     *
     * @param value 枚举类型
     * @param <T>
     * @return
     */
    public static <T extends Enum> EnumWrapper<T> of(Class<T> value) {
        return new EnumWrapper<>(value);
    }

    /**
     * 将包装类中的枚举类型转化为枚举的Stream
     *
     * @return
     */
    private Stream<T> convertEnumToStream(){
        return Stream.of(value.getEnumConstants());
    }

    /**
     * 将包装类中的枚举类型转化为Option对象
     *
     * @return
     */
    private List<Option> convertEnumToOptions(Function<Options, String> funName, Function<Options, String> funValue){
        return convertEnumToStream().map(item -> (Options) item)
                .map(item -> new Option(funName.apply(item), funValue.apply(item)))
                .collect(Collectors.toList());
    }
}
