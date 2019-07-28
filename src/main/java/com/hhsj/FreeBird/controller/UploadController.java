package com.hhsj.FreeBird.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.service.UserInfoService;
import com.hhsj.FreeBird.util.JsonResult;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

//import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadController {

    /**
     * 头像上传
     *
     * @param file    图片的基本信息
     * @param fileCut 前台剪切图片的基本信息
     * @param request
     * @return
     */

    @Resource
    private UserInfoService userInfoService;
    @RequestMapping("/h5upload")
    @PostMapping("/h5upload")
    @ResponseBody
    public JsonResult h5upload(
            @RequestParam(value = "avatar_file", required = true) MultipartFile file,
            @RequestParam(value = "avatar_data", required = true) String fileCut,
             HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        try {

            //获取根目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {path = new File("");}
            //上传目录为/static/images/upload/
            //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
            //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
            File root = new File(path.getAbsolutePath(), "static/images/qq/");
            if (!root.exists()) root.mkdirs();

            String orgFileName = file.getOriginalFilename();// 获取图片的名字
            String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
            File targetFile = new File(root, fileName);

            //multipartfile 转 File对象
            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File f = fi.getStoreLocation();
            file.transferTo(f);
            // JSON的对象格式的字符串转换成json数据
            JSONObject json = JSONObject.parseObject(fileCut);
            String x = json.get("x").toString();
            String y = json.get("y").toString();
            String width = json.get("width").toString();
            String height = json.get("height").toString();
            // 下面的要的数据时int类型，把小数去掉
            if (x.indexOf(".") > -1) {
                x = x.substring(0, x.indexOf("."));
            }
            if (y.indexOf(".") > -1) {
                y = y.substring(0, y.indexOf("."));
            }
            if (width.indexOf(".") > -1) {
                width = width.substring(0, width.indexOf("."));
            }
            if (height.indexOf(".") > -1) {
                height = height.substring(0, height.indexOf("."));
            }

            // 剪切图片
            BufferedImage image = ImageIO.read(f);

            image = image.getSubimage(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(width),
                    Integer.parseInt(height));

            // 压缩边长为100的正方形, 生成图片 到指定的位置
            Builder<BufferedImage> builder = null;
            builder = Thumbnails.of(image).size(100, 100);
            builder.toFile(targetFile);

            //更新用户头像
            jsonResult.setFlag(true);
            jsonResult.setResult("images/qq/" + fileName);
            Userinfo user = (Userinfo) session.getAttribute("user");
            user.setAvatar(fileName);
            userInfoService.update(user);

        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }
}

