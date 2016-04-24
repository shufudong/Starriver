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
import org.apache.shiro.SecurityUtils;
import org.jessezhu.starriver.model.Task;
import org.jessezhu.starriver.service.TaskService;
import org.jessezhu.starriver.service.impl.ShiroDbRealm.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jesse
 */
@RequestMapping(value = "/task")
@Controller
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(Task task,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "3") int rows) {
        ModelAndView result = new ModelAndView("task/taskList");
        List<Task> taskList = taskService.selectByUserId(getCurrentUserId(), task, page, rows);
        result.addObject("pageInfo", new PageInfo<>(taskList));
        result.addObject("queryParam", task);
        result.addObject("page", page);
        result.addObject("rows", rows);
        
        return result;
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView createForm() {
        ModelAndView result = new ModelAndView("task/taskForm");
        result.addObject("task", new Task());
        result.addObject("action", "create");
        return result;
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Task task, RedirectAttributes redirectAttributes) throws Exception {
        task.setUserId(getCurrentUserId());
        taskService.save(task);
        redirectAttributes.addAttribute("message", "创建任务成功");
        return "redirect:/task/";
    }
    
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id) throws Exception {
        ModelAndView result = new ModelAndView("task/taskForm");
        result.addObject("task", taskService.selectByKey(id));
        result.addObject("action", "update");
        return result;
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) throws Exception {
        taskService.updateNotNull(task);
        redirectAttributes.addAttribute("message", "更新任务成功");
        return "redirect:/task/";
    }
    
    @ModelAttribute
    public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, ModelAndView modelAndView) throws Exception {
        if (id != -1) {
            modelAndView.addObject("task", taskService.selectByKey(id));
        }
    }
    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) throws Exception {
        taskService.delete(id);
        redirectAttributes.addAttribute("message", "删除任务成功");
        return "redirect:/task/";
    }
    
    private Long getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }
}
