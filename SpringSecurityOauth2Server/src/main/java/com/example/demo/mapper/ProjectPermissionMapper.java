package com.example.demo.mapper;

import com.example.demo.entity.ProjectPermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectPermissionMapper extends Mapper<ProjectPermission> {
    List<ProjectPermission> getPermissionListByRoleId(int roleId);
}