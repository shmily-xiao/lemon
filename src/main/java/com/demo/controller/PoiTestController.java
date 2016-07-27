package com.demo.controller;

import com.demo.domain.BrandMobileInfoEntity;
import com.demo.domain.OrderPrice;
import com.demo.domain.Person;
import com.demo.service.PersonService;
import com.demo.service.UserService;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.core.config.Order;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by simpletour_java on 2015/9/8.
 */
@Controller
public class PoiTestController {

    @Resource
    private PersonService personService;

    @RequestMapping(value = "/importExcel")
    public String importExcel(Model model){
        return "poi/import";
    }

    @RequestMapping(value = "/importBrandSort", method = RequestMethod.POST)
    public String importBrandSort(@RequestParam("filename") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
        if (file == null)
            return null;

        String name = file.getOriginalFilename();// 获取上传文件名,包括路径
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0)
            return null;
        InputStream in = file.getInputStream();
        List<OrderPrice> orderPrices = importBrandPeriodSort(in);
//        int count = importBrandPeriodSort(in).size();
        List<OrderPrice> wechatFact = orderPrices.subList(0,44);
        List<OrderPrice> wechat = orderPrices.subList(45,orderPrices.size());

        List<OrderPrice> tempWechatFact = new ArrayList<>();
        List<OrderPrice> tempWechat = new ArrayList<>();

        for (OrderPrice orderPrice1:wechatFact){
            int ii = 0;
            for (OrderPrice orderPrice2:wechat){
                if (orderPrice1.getOrderId().equalsIgnoreCase(orderPrice2.getOrderId())){
                    ii = 1;
                    break;
                }
            }
            if (ii==0){
                tempWechatFact.add(orderPrice1);
            }
        }
        for (OrderPrice orderPrice1:wechat){
            int ii = 0;
            for (OrderPrice orderPrice2:wechatFact){
                if (orderPrice1.getOrderId().equalsIgnoreCase(orderPrice2.getOrderId())){
                    ii = 1;
                    break;
                }
            }
            if (ii==0){
                tempWechat.add(orderPrice1);
            }
        }

        String strAlertMsg ="";

//        strAlertMsg= "成功更新" + count + "条！";


        request.getSession().setAttribute("msg",strAlertMsg);
        return "success";
        //return null;
    }
    
    public List<OrderPrice> importBrandPeriodSort(InputStream in) throws Exception  {

        List<OrderPrice> brandMobileInfos = readBrandPeriodSorXls(in);
//        for (BrandMobileInfoEntity brandMobileInfo : brandMobileInfos) {
//            personService.updateByConditions(brandMobileInfo);
//            personService.insertByConditions(brandMobileInfo);
//        }
        return brandMobileInfos;
    }

    private List<OrderPrice> readBrandPeriodSorXls(InputStream is)
            throws IOException, ParseException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<OrderPrice> brandMobileInfos = new ArrayList<OrderPrice>();
        OrderPrice brandMobileInfo;
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                brandMobileInfo = new OrderPrice();
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//                    HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
                brandMobileInfo.setOrderId(hssfRow.getCell(0).toString().replace("\"", ""));
//                String info = hssfRow.getCell(1).toString();
                brandMobileInfo.setPrice(hssfRow.getCell(1).toString().replace("\"",""));
//                brandMobileInfo.setPrice(Integer.valueOf(info.substring(0, info.length()-2)));
//                brandMobileInfo.setId(hssfRow.getCell(2).toString().replace("\"", ""));
//                brandMobileInfo.setMobile(hssfRow.getCell(3).toString().replace("\"", ""));
//                brandMobileInfo.setPassword(hssfRow.getCell(4).toString().replace("\"",""));
                brandMobileInfos.add(brandMobileInfo);

            }
        }
        return brandMobileInfos;
    }
}
