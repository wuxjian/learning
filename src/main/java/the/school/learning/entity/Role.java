package the.school.learning.entity;

import java.io.Serializable;

/**
 * role
 * @author 
 */
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1管理员2普通用户
     */
    private String role;

    /**
     * 角色名称
     */
    private String roleName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}