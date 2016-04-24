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
package org.jessezhu.starriver.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.jessezhu.starriver.mapper.TaskMapper;
import org.jessezhu.starriver.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

/**
 *
 * @author jesse
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PageMapperTest {
    
    @Autowired
    private SqlSession sqlSession;
    
    @Test
    public void test(){
        TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        Example example = new Example(Task.class);
        example.createCriteria().andGreaterThan("id", 3);
        PageHelper.startPage(2, 3);
        List<Task> tasks = taskMapper.selectByExample(example);
        for(Task task : tasks){
            System.out.println(task.getTitle());
        }     
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);
        System.out.println("总数：" + pageInfo.getTotal());
    }
}
