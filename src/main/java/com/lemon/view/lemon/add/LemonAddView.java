package com.lemon.view.lemon.add;

import com.lemon.framework.enumwrapper.NameValuePair;
import com.lemon.framework.enumwrapper.Option;
import com.lemon.view.common.NameAndValueView;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
public class LemonAddView {

    // 内容的类型
    private List<Option> contentsType;

    // 策略的类型（可见的范围）
    private List<Option> strategyTypes;

    public List<Option> getContentsType() {
        return contentsType;
    }

    public void setContentsType(List<Option> contentsType) {
        this.contentsType = contentsType;
    }

    public List<Option> getStrategyTypes() {
        return strategyTypes;
    }

    public void setStrategyTypes(List<Option> strategyTypes) {
        this.strategyTypes = strategyTypes;
    }
}
