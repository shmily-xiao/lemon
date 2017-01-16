package com.lemon.manager.lemon;

import com.lemon.convertor.ConvertorResult;
import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.framework.enumwrapper.EnumWrapper;
import com.lemon.framework.enumwrapper.Option;
import com.lemon.service.IContentService;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.BeanLocator;
import com.lemon.view.lemon.add.LemonAddView;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/5.
 */
@Component
public class LemonContentsManager {

    @Resource
    private IContentService contentService;

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

    /**
     * 添加lemonContent内容
     * @param form
     * @param userId
     * @return
     */
    public Boolean addContent(LemonContentsAddForm form, Long userId){
        ConvertorResult convertorResult = (ConvertorResult) BeanLocator.findBeanByName("ContentFormToBo_Convertor");
        LemonContentAddBo lemonContentAddBo = (LemonContentAddBo) convertorResult.getResult(form, userId);
        return  contentService.addLemonContent(lemonContentAddBo);
    }


    public static void main(String[] args) {
        String date = "2016-04-11 10:00";
        System.out.println(LocalDateTime.parse(date));
        System.out.println(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE));

    }
}
