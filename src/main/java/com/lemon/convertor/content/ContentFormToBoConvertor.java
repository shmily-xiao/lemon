package com.lemon.convertor.content;

import com.lemon.convertor.ConvertorResult;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        addBo.setContent(StringUtils.replaceHtmlSpecialsToString(form.getContent()));
        addBo.setContentsType(form.getContentsType());
        addBo.setImages(form.getImages());
        addBo.setNeedMessage(form.getNeedMessage());
        addBo.setStrategyType(form.getStrategyType());
        addBo.setTitle(StringUtils.replaceHtmlSpecialsToString(form.getTitle()));
        // 2016-04-11T10:00
        // LocalDateTime.parse("2016-04-11 10:00".replace(" ","T"));
        if (StringUtils.notEmpty(form.getFinishedTime())) {
            addBo.setFinishedTime(LocalDateTime.parse(form.getFinishedTime().replace(" ", "T")));
        }
        addBo.setUserId(userId);

        return addBo;
    }

    public static void main(String[] args) {
        String ss = "<script>你好</script>";
//        String result = StringEscapeUtils.escapeXml("<script>你好</script>");
//        String result = htmlToText("<script>你好</script>");
//        String unresult = StringEscapeUtils.unescapeHtml(result);
        System.out.println(ss.replace("<","&lt;").replace(">","&gt;"));
        System.out.println(StringUtils.replaceHtmlSpecialsToString(ss));
//        System.out.println(StringUtils.replaceHtmlSpecials(ss));
//        System.out.println(unresult);
    }

}



