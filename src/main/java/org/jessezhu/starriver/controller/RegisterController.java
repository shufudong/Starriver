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
import org.jessezhu.starriver.model.User;
import org.jessezhu.starriver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(value = "/register")
@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String registerForm() {
        return "account/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid User user, RedirectAttributes redirectAttributes) throws Exception {
        accountService.registerUser(user);
        redirectAttributes.addFlashAttribute("loginName", user.getLoginName());
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping(value = "/checkLoginName")
    public String checkLoginName(@RequestParam("loginName") String loginName) throws Exception {
        if(accountService.selectByLoginName(loginName) == null){
            return "true";
        }else{
            return "false";
        }
    }
}
