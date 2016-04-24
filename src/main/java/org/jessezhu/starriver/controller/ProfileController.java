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

import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.jessezhu.starriver.model.User;
import org.jessezhu.starriver.service.AccountService;
import org.jessezhu.starriver.service.impl.ShiroDbRealm.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/profile")
@Controller
public class ProfileController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView updateForm() throws Exception {
        Long id = getCurrentUserId();
        ModelAndView result = new ModelAndView("account/profile");
        result.addObject("user", accountService.selectByKey(id));
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("user")User user) throws Exception{
        accountService.updateUser(user);
        updateCurrentUserName(user.getName());
        return "redirect:/";
    }
    
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1")Long id, ModelAndView modelAndView) throws Exception{
        if(id != -1){
            modelAndView.addObject("user", accountService.selectByKey(id));
        }
    }

    private Long getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }

    private void updateCurrentUserName(String name) {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        user.name = name;
    }
}
