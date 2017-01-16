package com.lemon.convertor.content;

import com.lemon.convertor.ConvertorResult;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by simpletour_Jenkin on 2017/1/16.
 */
@Component("ContentFormToBo_Convertor")
public class ContentFormToBoConvertor implements ConvertorResult<LemonContentAddBo>{
    @Override
    public LemonContentAddBo getResult(Object... obj) {
        LemonContentsAddForm form = (LemonContentsAddForm)obj[0];
        Long userId = (Long) obj[1];

        LemonContentAddBo addBo = new LemonContentAddBo();
        addBo.setContent(form.getContent());
        addBo.setContentsType(form.getContentsType());
        addBo.setImages(form.getImages());
        addBo.setNeedMessage(form.getNeedMessage());
        addBo.setStrategyType(form.getStrategyType());
        addBo.setTitle(form.getTitle());
        // 2016-04-11T10:00
        // LocalDateTime.parse("2016-04-11 10:00".replace(" ","T"));
        addBo.setFinishedTime(LocalDateTime.parse(form.getFinishedTime().replace(" ","T")));
        addBo.setUserId(userId);

        return addBo;
    }
}
