package top.sea521.service;

import top.sea521.po.Role;

/**
 *  Role 角色权限表Service层
 */
public interface RoleService {

    Role findByid(Integer id) throws Exception;

}
