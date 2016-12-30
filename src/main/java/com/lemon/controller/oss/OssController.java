package com.lemon.controller.oss;

import com.aliyun.oss.OSSClient;
import com.lemon.form.AjaxResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Created by simpletour_Jenkin on 2016/12/30.
 */
@Controller
public class OssController {

    @Value("${aliyunoss.endpoint}")
    private String endpoint;

    @Value("${aliyunOss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyunOss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyunOss.bucketName}")
    private String bucketName;


    @RequestMapping(value = "lemon/image/add/test")
    public String test(){
        return "lemon/oss/index";
    }

    @ResponseBody
    @RequestMapping(value = "lemon/image/add",method = RequestMethod.POST)
    public AjaxResponse add(@RequestParam("file") MultipartFile file){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 上传文件
            ossClient.putObject(bucketName, "<yourKey>", file.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
            return AjaxResponse.fail().reason(e.getMessage()).msg("文件不合法");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResponse.fail();
        }finally {
            // 关闭client
            ossClient.shutdown();
        }

        return AjaxResponse.ok();
    }




}
