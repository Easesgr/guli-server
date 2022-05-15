package com.anyi.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.anyi.commonutils.R;
import com.anyi.commonutils.ResultCode;
import com.anyi.servicebase.exception.GuliException;
import com.anyi.vod.init.AliyunVodSDKUtils;
import com.anyi.vod.init.ConstantPropertiesUtil;
import com.anyi.vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
=======
import org.apache.poi.util.StringUtil;
>>>>>>> 7fddefb (整合springcloud)
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 7fddefb (整合springcloud)

/**
 * @author 安逸i
 * @version 1.0
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {
    // 上传视频
    @Override
    public R uploadVodFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0,
                    originalFilename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" +
                        response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new GuliException(ResultCode.ERROR, errorMessage);
                }
            }
            return R.ok().data("videoId",videoId);
        } catch (IOException e) {
            throw new GuliException(ResultCode.ERROR, "guli vod 服务上传失败");
        }
    }

    // 删除视频
    @Override
    public R deleteVod(String vodId) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(vodId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (ClientException e){
            throw new GuliException(ResultCode.ERROR, "视频删除失败");
        }
        return R.ok();
    }
<<<<<<< HEAD
=======

    @Override
    public R batchDeleteVideo(List<String> videoIds) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 将id生成 "," 隔开的字符串
            String ids = StringUtil.join(videoIds.toArray(), ",");

            request.setVideoIds(ids);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (ClientException e){
            throw new GuliException(ResultCode.ERROR, "视频删除失败");
        }
        return R.ok();
    }
>>>>>>> 7fddefb (整合springcloud)
}
