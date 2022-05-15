package com.anyi.vod.service;

import com.anyi.commonutils.R;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 7fddefb (整合springcloud)
/**
 * @author 安逸i
 * @version 1.0
 */
public interface VodService {
    R uploadVodFile(MultipartFile file);
    // 删除视频
    R deleteVod(String vodId);
<<<<<<< HEAD
=======
    // 批量删除视频
    R batchDeleteVideo(List<String> videoIds);
>>>>>>> 7fddefb (整合springcloud)
}
