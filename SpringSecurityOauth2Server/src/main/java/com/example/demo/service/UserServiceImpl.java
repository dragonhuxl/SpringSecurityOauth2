package com.example.demo.service;

import com.example.demo.entity.ProjectPermission;
import com.example.demo.entity.ProjectRole;
import com.example.demo.entity.ProjectUser;
import com.example.demo.mapper.ProjectPermissionMapper;
import com.example.demo.mapper.ProjectRoleMapper;
import com.example.demo.mapper.ProjectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cuixb
 * @ClassName IUserServiceImpl
 * @Description TODO
 * @Date 2018/10/25 下午 4:40
 **/
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private ProjectUserMapper userDao;

    @Autowired
    private ProjectRoleMapper roleDao;

    @Autowired
    private ProjectPermissionMapper projectPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProjectUser user = userDao.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
                getAuthority(user.getId()));
    }

    private List<SimpleGrantedAuthority> getAuthority(int userId) {
        //角色列表
        List<ProjectRole> roleList = roleDao.getRolesByUserId(userId);
        for (ProjectRole role : roleList) {
            int roleId = role.getId();
            List<ProjectPermission> projectPermissionList = projectPermissionMapper.getPermissionListByRoleId(roleId);
            if (null != projectPermissionList && projectPermissionList.size() > 0) {
                List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                for (ProjectPermission projectPermission : projectPermissionList) {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(projectPermission.getName());
                    simpleGrantedAuthorities.add(simpleGrantedAuthority);
                }
                return simpleGrantedAuthorities;
            }
        }

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_TOURIST"));
    }

}
