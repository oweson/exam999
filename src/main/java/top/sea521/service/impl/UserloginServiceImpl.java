package top.sea521.service.impl;

import top.sea521.mapper.UserloginMapper;
import top.sea521.po.Userlogin;
import top.sea521.po.UserloginExample;
import top.sea521.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;

    /**
     * 1 根据用户名登录前的查询
     */
    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);
        if (list != null) {
            /**集合不为空，取出第一个*/
            return list.get(0);
        }

        return null;
    }

    /**
     * 2 保存登录用户
     */
    public void save(Userlogin userlogin) throws Exception {

        userloginMapper.insert(userlogin);
    }

    /**
     * 3 根据用户名取出去除用户
     */
    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.deleteByExample(userloginExample);
    }

    @Override
    /** 4 格局用户名更新登录信息和用户信息*/
    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

}
