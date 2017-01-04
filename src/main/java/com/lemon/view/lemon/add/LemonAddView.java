package com.lemon.view.lemon.add;

import com.lemon.view.common.NameAndValueView;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
public class LemonAddView {

    // 内容的类型
    private List<NameAndValueView> contentsType;

    // 策略的类型（可见的范围）
    private List<NameAndValueView> strategyTypes;

    public List<NameAndValueView> getContentsType() {
        return contentsType;
    }

    public void setContentsType(List<NameAndValueView> contentsType) {
        this.contentsType = contentsType;
    }

    public List<NameAndValueView> getStrategyTypes() {
        return strategyTypes;
    }

    public void setStrategyTypes(List<NameAndValueView> strategyTypes) {
        this.strategyTypes = strategyTypes;
    }
}
