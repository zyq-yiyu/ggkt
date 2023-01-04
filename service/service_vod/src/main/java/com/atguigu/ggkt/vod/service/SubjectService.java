package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-01-04
 */
public interface SubjectService extends IService<Subject> {

    //课程分类列表
    //懒加载,每次查询一层数据
    List<Subject> selectList(Long id);

    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
