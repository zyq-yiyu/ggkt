package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-01-04
 */
@RestController
@RequestMapping(value = "/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //课程分类列表
    //懒加载,每次查询一层数据
    //查询下一层课程分类
    //根据parent_id
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectList(id);
        return Result.ok(list);
    }

    //课程分类导出
    @ApiOperation(value="课程分类导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    //课程分类导入
    @ApiOperation(value = "课程分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importDictData(file);
        return Result.ok();
    }
}

