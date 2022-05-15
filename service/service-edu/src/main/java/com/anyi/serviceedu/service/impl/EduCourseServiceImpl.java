package com.anyi.serviceedu.service.impl;

import com.anyi.commonutils.R;
import com.anyi.commonutils.ResultCode;
import com.anyi.servicebase.exception.GuliException;
import com.anyi.serviceedu.entity.EduChapter;
import com.anyi.serviceedu.entity.EduCourse;
import com.anyi.serviceedu.entity.EduCourseDescription;
import com.anyi.serviceedu.entity.EduVideo;
import com.anyi.serviceedu.entity.query.CourseQuery;
import com.anyi.serviceedu.entity.vo.CoursePublishVo;
import com.anyi.serviceedu.entity.vo.CourseVo;
import com.anyi.serviceedu.mapper.EduCourseMapper;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 7fddefb (整合springcloud)
import com.anyi.serviceedu.service.EduChapterService;
import com.anyi.serviceedu.service.EduCourseDescriptionService;
import com.anyi.serviceedu.service.EduCourseService;
import com.anyi.serviceedu.service.EduVideoService;
<<<<<<< HEAD
=======
=======
import com.anyi.serviceedu.service.*;
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.ConstantTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author anyi
 * @since 2022-05-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService descriptionService;

    @Resource
    private EduVideoService videoService;

    @Resource
    private EduChapterService chapterService;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    @Resource
    private VodService vodService;

>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
    @Override
    public R addCourseInfo(CourseVo courseVo) {
        // 1. 保存课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseVo, eduCourse);
        boolean flag = save(eduCourse);
        if (!flag){
            throw new GuliException(ResultCode.ERROR,"添加课程失败");
        }

        // 2. 带上课程id保存课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseVo, eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        descriptionService.save(eduCourseDescription);
        // 3. 返回课程id
        return R.ok().data("courseId",eduCourse.getId());
    }
    // 根据课程id获取课程信息
    @Override
    public R getCourseById(String courseId) {
        // 1.获取course信息
        EduCourse course = getById(courseId);

        // 2. 获取course 描述信息
        EduCourseDescription description = descriptionService.getById(courseId);
        // 3. 封装称vo对象，返回信息
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(course, courseVo);

        courseVo.setDescription(description.getDescription());

        return R.ok().data("courseVo",courseVo);
    }

    // 更新course信息
    @Override
    public R updateCourse(CourseVo courseVo) {
        // 1. 保存课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseVo, eduCourse);
        boolean flag = saveOrUpdate(eduCourse);
        if (!flag){
            throw new GuliException(ResultCode.ERROR,"修改课程失败");
        }

        // 2. 带上课程id保存课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseVo, eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        descriptionService.saveOrUpdate(eduCourseDescription);
        // 3. 返回课程id
        return R.ok().data("courseId",eduCourse.getId());
    }
    // 获取发布课程信息
    @Override
    public R getPublishCourseInfo(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }


    // 根据id删除课程
    @Override
    public R deleteCourseById(String id) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
        // 删除小节中的视频
        // 根据id 查出所以该课程的小节视频
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id");
        videoQueryWrapper.eq("course_id", id);
        List<String > idList = new ArrayList<>();
        List<EduVideo> videoList = videoService.list(videoQueryWrapper);
        if (videoList.size() == 0){
            throw new GuliException(ResultCode.ERROR,"没有课程视频删除失败");
        }
        for (EduVideo video : videoList) {
            idList.add(video.getVideoSourceId());
        }

        vodService.batchDeleteVideo(idList);
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
        // 删除小节
        boolean videoFlag = videoService.remove(new QueryWrapper<EduVideo>().eq("course_id", id));
        if (!videoFlag){
            throw new GuliException(ResultCode.ERROR,"删除小节失败");
        }
        // 删除章节
        boolean chapterFlag = chapterService.remove(new QueryWrapper<EduChapter>().eq("course_id", id));
        if (!chapterFlag){
            throw new GuliException(ResultCode.ERROR,"删除章节失败");
        }
        // 删除描述
        descriptionService.removeById(id);
        // 删除课程
        boolean b = removeById(id);
        if (!b){
            throw new GuliException(ResultCode.ERROR,"删除课程失败");
        }
        // 返回结果
        return R.ok();
    }
    // 分页查询和条件查询课程
    @Override
    public R getPageCourse(Integer page, Integer limit, CourseQuery courseQuery) {
        Page<EduCourse> pageCondition = new Page<>(page, limit);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();
        if (!StringUtils.isEmpty(subjectId )){
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(title)){
            wrapper.eq("title", title);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<EduCourse> pageInfo = page(pageCondition, wrapper);
        return R.ok().data("total",pageInfo.getTotal()).data("courses",pageInfo.getRecords());
    }

}
