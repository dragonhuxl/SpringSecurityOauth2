package com.example.demo.entity;

import javax.persistence.*;

@Table(name = "project_role")
public class ProjectRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_level")
    private String roleLevel;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return role_level
     */
    public String getRoleLevel() {
        return roleLevel;
    }

    /**
     * @param roleLevel
     */
    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }
}