package com.example.demo.mapper;

import com.example.demo.entity.ProjectRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectRoleMapper extends Mapper<ProjectRole> {
    List<ProjectRole> getRolesByUserId(int userId);
}