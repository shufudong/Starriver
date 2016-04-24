/*
 * The MIT License
 *
 * Copyright 2016  jesse.zwd@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jessezhu.starriver.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.jessezhu.starriver.model.User;
import org.jessezhu.starriver.service.AccountService;
import static org.jessezhu.starriver.service.impl.AccountConstant.HASH_INTERATIONS;
import org.jessezhu.starriver.util.Clock;
import org.jessezhu.starriver.util.Digests;
import org.jessezhu.starriver.util.Encodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service("accountService")
public class AccountServiceImpl extends BaseService<User> implements AccountService {

    private static final int SALT_SIZE = 8;
    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private Clock clock = Clock.Default;

    @Override
    public List<User> selectByUser(User user, int page, int rows) {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (StringUtil.isNotEmpty(user.getName())) {
            criteria.andEqualTo("name", user.getName());
        }
        if (StringUtil.isNotEmpty(user.getLoginName())) {
            criteria.andEqualTo("loginName", user.getLoginName());
        }
        if (user.getRegisterDate() != null) {
            criteria.andEqualTo("registerDate", user.getRegisterDate());
        }

        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    @Override
    public void registerUser(User user) throws Exception {
        entryptPassword(user);
        user.setRoles("user");
        user.setRegisterDate(clock.getCurrentDate());
        save(user);
    }

    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Override
    public User selectByLoginName(String loginName) {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", loginName);
        List<User> userList = selectByExample(example);
        if (userList.size() > 0) {
            User user = userList.get(0);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void updateUser(User user) {
        if (StringUtil.isNotEmpty(user.getPlainPassword())) {
            entryptPassword(user);
        }
        updateNotNull(user);
    }

    @Override
    public void deleteById(Long id) {
        if (isSupervisor(id)) {
            logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
            return;
        }
        delete(id);
    }

    private boolean isSupervisor(Long id) {
        return id == 1;
    }

    private String getCurrentUserName() {
        return "";
    }

}
