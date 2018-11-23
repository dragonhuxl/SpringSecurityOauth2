package com.example.demo.mapper;

import com.example.demo.entity.ProjectUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectUserMapper extends Mapper<ProjectUser> {
    ProjectUser findUserById(int userId);
    ProjectUser findUserByName(String userName);

    List<ProjectUser> getUserListByCondition(@Param("condition") String condition);
}