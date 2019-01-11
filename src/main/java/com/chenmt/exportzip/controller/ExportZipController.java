package com.chenmt.exportzip.controller;

import com.chenmt.exportzip.utils.FileUtils;
import com.chenmt.exportzip.utils.WordUtils;
import com.chenmt.exportzip.utils.ZipUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program exportzip
 * @description: zip压缩包导出
 * @author: chenmet
 * @create: 2019/01/11 09:35
 */
@RestController
public class ExportZipController {


    @RequestMapping("export_zip")
    public void exportZip(HttpServletResponse response){
        String path = this.getClass().getClassLoader().getResource("application.yml").getPath();
        String wordPath= FileUtils.getPath(path,FileUtils.WORD_PATH);
        String zipPath=FileUtils.getPath(path,FileUtils.ZIP_PATH);
        Map<String,Object> param=new HashMap<>();
        param.put("title","这是word标题");
        param.put("content","这是内容");
        param.put("name","这是名字");
        param.put("year", DateFormatUtils.format(new Date(), "yyyy"));
        param.put("month",DateFormatUtils.format(new Date(), "MM"));
        param.put("day",DateFormatUtils.format(new Date(), "dd"));
        WordUtils.saveWord(param,wordPath);
        ZipUtils.saveZip(wordPath,zipPath,param.get("name").toString(),response);
    }


}
