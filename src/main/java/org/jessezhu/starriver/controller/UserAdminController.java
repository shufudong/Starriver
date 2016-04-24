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
package org.jessezhu.starriver.controller;

import com.github.pagehelper.PageInfo;
import java.util.List;
import javax.validation.Valid;
import org.jessezhu.starriver.model.User;
import org.jessezhu.starriver.service.AccountService;
import org.jessezhu.starriver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(value = "/admin/user")
@Controller
public class UserAdminController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(User user,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "3") int rows) throws Exception {
        ModelAndView result = new ModelAndView("account/adminUserList");
        List<User> userList = accountService.selectByUser(user, page, rows);
        result.addObject("pageInfo", new PageInfo<>(userList));
        result.addObject("queryParam", user);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id) throws Exception {
        ModelAndView result = new ModelAndView("account/adminUserForm");
        result.addObject("user", accountService.selectByKey(id));
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) throws Exception {
        String loginName = user.getLoginName();
        accountService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "更新用户" + loginName + "成功");
        return "redirect:/task/";
    }
    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id")Long id, RedirectAttributes redirectAttributes) throws Exception{
        User user = accountService.selectByKey(id);
        taskService.deleteByUserId(id);
        accountService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
        return "redirect:/admin/user";
    }
     

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, ModelAndView modelAndView) throws Exception {
        if (id != -1) {
            modelAndView.addObject("user", accountService.selectByKey(id));
        }
    }
}
