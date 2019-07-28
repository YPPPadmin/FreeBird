package com.hhsj.FreeBird.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传工具
 */
public class UploadUtil {

	/**
	 * 处理文件上传
	 *
	 * @param file
	 * @param basePath
	 *            图片要上传到的服务器路径
	 * @return
	 * @throws IOException
	 */
	public static String upload(MultipartFile file, String basePath) throws IOException {
		// 创建文件夹
		File uploadFile = new File(basePath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		String orgFileName = file.getOriginalFilename();//获取图片的名字
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
		File targetFile = new File(basePath, fileName);

		// multipartfile 转 File对象
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();

		compressPicture(f, targetFile);
		// 下面这个是按比例压缩的，最后得到的图片可能不是正方形
		// Thumbnails.of(f).size(50, 50).toFile(targetFile);

		return fileName;
	}

	// 压缩至指定图片尺寸（例如：横50高50），保持图片不变形，多余部分裁剪掉
	public static void compressPicture(File fromPic, File toPic) throws IOException {
		BufferedImage image = ImageIO.read(fromPic);
		Builder<BufferedImage> builder = null;

		int imageWidth = image.getWidth();
		int imageHeitht = image.getHeight();
		if ((float) 50 / 50 != (float) imageWidth / imageHeitht) {
			if (imageWidth > imageHeitht) {
				image = Thumbnails.of(fromPic).height(50).asBufferedImage();
			} else {
				image = Thumbnails.of(fromPic).width(50).asBufferedImage();
			}
			builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, 50, 50).size(50, 50);
		} else {
			builder = Thumbnails.of(image).size(50, 50);
		}
		builder.outputFormat("jpg").toFile(toPic);
	}
}
