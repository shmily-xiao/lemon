package com.lemon.manager.lemon;

import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;
import com.lemon.framework.enumwrapper.EnumWrapper;
import com.lemon.framework.enumwrapper.Option;
import com.lemon.view.lemon.add.LemonAddView;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/5.
 */
@Component
public class LemonContentsManager {

    /**
     * 初始化添加页面的数据
     * @return
     */
    public LemonAddView initAddView(){

        LemonAddView addView = new LemonAddView();
        List<Option> contentsTypes = EnumWrapper.of(ContentType.class).getAllOptionsList();
        List<Option> strategyTypes = EnumWrapper.of(StrategyType.class).getAllOptionsList();

        addView.setContentsType(contentsTypes);
        addView.setStrategyTypes(strategyTypes);
        return addView;
    }
}
