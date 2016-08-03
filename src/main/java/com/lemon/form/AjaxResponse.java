package com.lemon.form;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simpletour_Jenkins on 14-11-6.
 */
public abstract class AjaxResponse {
    /**
     * 成功
     * @return
     */
    public static final AjaxResponse ok(){return new BaseDataResponse(0);}

    /**
     * 成功
     * @param source
     * @return
     */
    public static final AjaxResponse ok(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(0);
        response.setMessageSource(source);
        return response;
    }
    /**
     * 失败
     * @return
     */
    public static final AjaxResponse fail(){
        return new BaseDataResponse(1);
    }
    /**
     * 失败
     * @param source
     * @return
     */
    public static final AjaxResponse fail(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(1);
        response.setMessageSource(source);
        return response;
    }

    /**
     * 自定义错误码
     * @param code
     * @return
     */
    public static final AjaxResponse fail(int code){
        return new BaseDataResponse(code);
    }
    /**
     * 无数据
     * @return
     */
    public static final AjaxResponse noData(){
        return new BaseDataResponse(2);
    }
    /**
     * 无数据
     * @param source
     * @return
     */
    public static final AjaxResponse noData(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(2);
        response.setMessageSource(source);
        return response;
    }
    /**
     *　表单验证失败
     * @return
     */
    public static final AjaxResponse validationFail(){
        return new BaseDataResponse(100);
    }
    /**
     * 表单验证失败
     * @param source
     * @return
     */
    public static final AjaxResponse validationFail(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(100);
        response.setMessageSource(source);
        return response;
    }
    /**
     *　没有权限
     * @return
     */
    public static final AjaxResponse noAccess(){
        return new AjaxResponse(200){};
    }
    /**
     * 没有权限
     * @param source
     * @return
     */
    public static final AjaxResponse noAccess(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(200);
        response.setMessageSource(source);
        return response;
    }
    /**
     *　未登录
     * @return
     */
    public static final AjaxResponse nologin(){
        return new AjaxResponse(300){};
    }
    /**
     * 未登录
     * @param source
     * @return
     */
    public static final AjaxResponse nologin(MessageSource source){
        BaseDataResponse response=new BaseDataResponse(300);
        response.setMessageSource(source);
        return response;
    }
    /**
     * 服务不可用
     * @param reason
     * @return
     */
    public static final AjaxResponse unavailable(String... reason){
        return new AjaxResponse(500){};
    }

    @JSONField(ordinal = 1, name = "code")
    private final int code;

    @JSONField(ordinal = 2, name = "msg")
    private String msg ;

    @JSONField(ordinal = 3, name = "reason")
    private String reason ;

    @JSONField(ordinal = 4, name = "url")
    private String url;

    @JSONField(ordinal = 5, name = "data")
    protected Object data;

    @JSONField(ordinal = 6,name = "tip")
    protected Map<String,NameTip> tips;

    @JSONField(ordinal = 7,name ="html")
    private String html;
    @JSONField(serialize = false)
    private MessageSource messageSource;

    private AjaxResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String,NameTip> getTips(){return tips;}

    public Object getData() {
        return data;
    }

    public String getUrl() {return url;}

    public String getReason() {
        return reason;
    }

    public String getHtml() {return html;}

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * response message
     * @param msg
     * @return
     */
    @SuppressWarnings({"varargs"})
    public AjaxResponse msg(String msg){
        if (this.getMessageSource()!=null && msg.contains("."))
            this.msg =messageSource.getMessage(msg,null,null);
        else
            this.msg=msg;
        return this;
    }

    public AjaxResponse reason(String reason){
        if (this.getMessageSource()!=null && reason.contains("."))
            this.reason =messageSource.getMessage(reason,null,null);
        else
            this.reason=reason;
        return this;
    }

    public AjaxResponse reason(Exception e){
        if (this.getMessageSource()!=null && e.getMessage().contains("."))
            this.reason =messageSource.getMessage(e.getMessage(),null,null);
        else
            this.reason =e.getMessage();
        return this;
    }

    public AjaxResponse url(String url){
        this.url = url;
        return this;
    }

    public AjaxResponse html(String html){
        this.html=html;
        return this;
    }

    /**
     * response data
     * @param data
     * @return
     */
    public AjaxResponse data(Object data){
        throw new UnsupportedOperationException("unsupported operation");
    }

    /**
     * response data
     * @param bindingResult
     * @return
     */
    public AjaxResponse tips(BindingResult bindingResult){
        throw new UnsupportedOperationException("unsupported operation");
    }

    /**
     *
     * @return
     */
    public String toJSONString(){
        SerializerFeature[] features = new SerializerFeature[]{
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.SortField,
                SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect
        };
        return JSONObject.toJSONString(this, features);
    }


    /**
     *
     */
    private static class BaseDataResponse extends AjaxResponse {

        private BaseDataResponse(int code) {
            super(code);
        }

        @Override
        public AjaxResponse data(Object data) {
            this.data = data;
            return this;
        }

        @Override
        public AjaxResponse tips(BindingResult bindingResult) {
            this.tips=new HashMap<String,NameTip>();
            for (FieldError fieldError:bindingResult.getFieldErrors()){
                if (tips.get(fieldError.getField())==null)
                    tips.put(fieldError.getField(),new NameTip());
                boolean isExisted=false;
                for (Tip tip:tips.get(fieldError.getField()).getTips()){
                    if (fieldError.getDefaultMessage().equals(tip.getTip()))
                        isExisted=true;
                }
                if (!isExisted) {
                    if (this.getMessageSource()==null || !fieldError.getDefaultMessage().contains("."))
                        tips.get(fieldError.getField()).getTips().add(new Tip(1, fieldError.getDefaultMessage()));
                    else
                        tips.get(fieldError.getField()).getTips().add(new Tip(1, this.getMessageSource().getMessage(fieldError.getDefaultMessage(),null,null)));
                }
            }

            if (bindingResult.hasGlobalErrors()) {
                this.msg(bindingResult.getGlobalError().getDefaultMessage());
            }else{
                if (this.getTips().size()==1){
                    for (NameTip nameTip:this.getTips().values()){
                        if (nameTip.getTips()!=null && nameTip.getTips().size()>0) {
                            this.msg(nameTip.getTips().get(0).getTip());
                            break;
                        }
                    }
                }
            }

            return this;
        }
    }

}


class NameTip{
    List<Tip> tips;

    public NameTip(){
        this.tips=new ArrayList<>();
    }

    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}

class Tip{
    //error=0,表示正确;error=1表示错误
    Integer error;
    String tip;

    public Tip(){}

    public Tip(Integer err,String t){
        this.error=err;
        this.tip=t;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}