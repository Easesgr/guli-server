package com.anyi.vod.controller;

import com.anyi.commonutils.R;
import com.anyi.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)

/**
 * @author 安逸i
 * @version 1.0
 */
@Api(tags = "视频操作")
@RestController
@CrossOrigin
@RequestMapping("/vod-service")
public class VodController {
    @Resource
    private VodService vodService;


    // 上传视频
    @ApiOperation("上传视频")
    @PostMapping("/uploadVod")
    public R uploadVod(MultipartFile file){
        return vodService.uploadVodFile(file);
    }
    // 删除视频
    @ApiOperation("删除视频")
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVod(@PathVariable String id){
        return vodService.deleteVod(id);
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

    @ApiOperation("批量删除视频")
    @DeleteMapping("/batchDeleteVideo")
    public R batchDeleteVideo(@RequestParam("videoIds") List<String > videoIds){
        return vodService.batchDeleteVideo(videoIds);
    }
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
}
