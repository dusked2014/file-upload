package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.neo.conf.AllConfig;
import com.neo.domain.AjaxResult;
import com.neo.domain.NeoFile;
import com.neo.service.NeoFileService;
import com.neo.utils.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文件控制器
 */
@Controller
public class FileController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    NeoFileService neoFileService;

    /**
     * 上传文件
     *
     * @param file
     * @return AjaxResult
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("fileName") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String fileName = FileUploadUtils.upload(AllConfig.getAvatarPath(), file);

                NeoFile neoFile = new NeoFile();
                neoFile.setCreateTime(new Date());
                neoFile.setFileName(fileName);
                neoFile.setFilePath("/imgs/");
                neoFileService.saveNeoFile(neoFile);

                return success(fileName);
            }
            return error();
        } catch (Exception e) {
            log.error("上传文件失败！", e);
            return error(e.getMessage());
        }
    }

    @GetMapping("/queryImage")
    @ResponseBody
    public AjaxResult queryImage(String fileName,String fileLinkUrl) {
        String fileName1 = "default";
        try {
            NeoFile neoFile = new NeoFile();
            neoFile.setFileName(fileName);
            neoFile.setFileLinkUrl(fileLinkUrl);
            NeoFile neoFile1 = neoFileService.queryNeoFile(neoFile);
            if ("true".equalsIgnoreCase(fileLinkUrl)){
                fileName1 = neoFile1.getFileLinkUrl();
            }else {
                fileName1 = neoFile1.getFileName();
            }
        } catch (Exception e) {
            log.error("加载失败！", e);
            return error(e.getMessage());
        }
        return success(fileName1);
    }

    @GetMapping("/updateImage")
    @ResponseBody
    public AjaxResult updateImage(String fileName,String fileLinkUrl) {
        NeoFile neoFile = new NeoFile();
        neoFile.setFileName(fileName);
        neoFile.setFileLinkUrl(fileLinkUrl);
        try {
            neoFileService.saveNeoFile(neoFile);
        } catch (Exception e) {
            log.error("更新文件名异常！", e);
            return error(e.getMessage());
        }
        return success(JSON.toJSONString(neoFile));
    }

    @GetMapping("/upload")
    public String upload() {
        return "fileTest";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


}