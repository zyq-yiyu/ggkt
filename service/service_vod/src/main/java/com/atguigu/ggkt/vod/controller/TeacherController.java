package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-12-30
 */

@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/vod/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    //http://localhost : 8301/vod/teacher/findAll
    //1 查询所有讲师列表
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        //模拟异常
//        try {
//            int a = 10/0;
//        }catch(Exception e) {
//            throw new GgktException(20001,"出现自定义异常");
//        }

        //调用service方法
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    //2 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //3 条件查询分页列表
    @ApiOperation(value = "获取分页列表")
    @PostMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);
        //获取条件值
        String name = teacherQueryVo.getName();//讲师名称
        Integer level = teacherQueryVo.getLevel();//讲师级别
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
        //封装条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("join_date", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("join_date", joinDateEnd);
        }
        //调用方法得到分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    //4 添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("save")
    public Result save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok(null);
    }

    //5修改-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //6修改-最终实现
    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    //7批量删除讲师
    // json数组[1,2,3]
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        teacherService.removeByIds(idList);
        return Result.ok(null);
    }
}

