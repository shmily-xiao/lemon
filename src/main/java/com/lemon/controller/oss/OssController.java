package com.lemon.controller.oss;

import com.aliyun.oss.OSSClient;
import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.form.AjaxResponse;
import com.lemon.pojo.constants.LemonConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by simpletour_Jenkin on 2016/12/30.
 * 用户上传图片使用
 *
 */
@Controller
public class OssController extends BaseController{

    @Value("${aliyunoss.endpoint}")
    private String endpoint;

    @Value("${aliyunOss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunOss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunOss.bucketName}")
    private String bucketName;

    @Value("${aliyunOss.hostUrl}")
    private String aliyunOssHostUrl;


//    @RequestMapping(value = "lemon/image/add/test")
    public String test(){
        return "lemon/oss/index";
    }

    /**
     * 现在只能单张的存放图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "lemon/image/add",method = RequestMethod.POST)
    public AjaxResponse add(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if (!super.isUserLoginIn(request)){
            return AjaxResponse.fail().msg("用户没有登录").url(LemonConstants.LOGIN_URL);
        }
        Long userId = super.getUserInfoUserID(request);
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String objectKeyName = userId+System.currentTimeMillis()+suffix;
        try {
            // 上传文件
            ossClient.putObject(bucketName,objectKeyName, file.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
            return AjaxResponse.fail().reason("上传文件异常，请联系管理员1350468513@qq.com").msg("文件不合法");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResponse.fail();
        }finally {
            // 关闭client
            ossClient.shutdown();
        }

        return AjaxResponse.ok().data(aliyunOssHostUrl+"/"+objectKeyName);
    }




}
