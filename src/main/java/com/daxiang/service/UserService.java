package com.daxiang.service;

import com.alibaba.fastjson.JSONObject;
import com.daxiang.mbg.mapper.UserMapper;
import com.daxiang.utils.TokenUtil;
import com.daxiang.mbg.po.User;
import com.daxiang.mbg.po.UserExample;
import com.daxiang.model.Response;
import com.daxiang.model.UserCache;
import com.daxiang.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;


/**
 * Created by jiangyitao.
 */
@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LdapTemplate ldapTemplate;

    //域名后缀
    @Value("${ldap.domainName}")
    private String ldapDomainName;

    public Response login(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        User query = new User();
        query.setUsername(user.getUsername());
        query.setPassword(user.getPassword());

        List<User> users = selectByUser(query);

        if (CollectionUtils.isEmpty(users)) {
            return Response.fail("账号或密码错误");
        } else {
            user = users.get(0);
            UserVo userVo = UserVo.convert(user, TokenUtil.create(user.getId() + ""));
            UserCache.add(user.getId(), user);
            return Response.success("登录成功", userVo);
        }
    }


    public Response loginByLDAP(User user) {
        // 判断数据库有无该账户
        user.setNickName(user.getUsername());
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getUsername());
        if (ldapTemplate.authenticate("", filter.toString(), user.getPassword())) {
            List<User> users = selectByUser2(user);
            if (CollectionUtils.isEmpty(users)) {
                // 没有用户，则插入数据库
                user.setNickName(user.getUsername());
                user.setCreateTime(new Date());
                user.setPassword("123456");
                if (userMapper.insertSelective(user) == 1) {
                    System.out.println("报错成功");
                }
            }
            List<User> users2 = selectByUser2(user);

            UserVo userVo = UserVo.convert(user, TokenUtil.create(users2.get(0).getId() + ""));
            UserCache.add(users2.get(0).getId(), user);
            return Response.success("登录成功", userVo);
        } else {
            return Response.fail("账号或密码错误");
        }
    }

    public Response register(User user) {
        if (StringUtils.isEmpty(user.getNickName())) {
            return Response.fail("昵称不能为空");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreateTime(new Date());

        int insertRow;
        try {
            insertRow = userMapper.insertSelective(user);
        } catch (DuplicateKeyException e) {
            return Response.fail("用户名已存在");
        }
        return insertRow == 1 ? Response.success("注册成功") : Response.fail("注册失败，请稍后重试");
    }

    public Response getInfo() {
        System.out.println("=========================");
        System.out.println(getUid());
        System.out.println("=========================");

        User user = UserCache.getById(getUid());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getNickName());
        jsonObject.put("avatar", "");
        jsonObject.put("introduction", "");
        jsonObject.put("roles", Arrays.asList("admin"));

        return Response.success(jsonObject);
    }

    public Response logout() {
        return Response.success();
    }

    public List<User> selectAll() {
        return selectByUser(null);
    }

    public List<User> selectByUser(User user) {
        if (user == null) {
            user = new User();
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        if (user.getId() != null) {
            criteria.andIdEqualTo(user.getId());
        }
        if (!StringUtils.isEmpty(user.getUsername())) {
            criteria.andUsernameEqualTo(user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            criteria.andPasswordEqualTo(user.getPassword());
        }
        if (!StringUtils.isEmpty(user.getNickName())) {
            criteria.andNickNameEqualTo(user.getNickName());
        }

        return userMapper.selectByExample(userExample);
    }

    public List<User> selectByUser2(User user) {
        if (user == null) {
            user = new User();
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        if (user.getId() != null) {
            criteria.andIdEqualTo(user.getId());
        }
        if (!StringUtils.isEmpty(user.getUsername())) {
            criteria.andUsernameEqualTo(user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getNickName())) {
            criteria.andNickNameEqualTo(user.getNickName());
        }

        return userMapper.selectByExample(userExample);
    }
}
