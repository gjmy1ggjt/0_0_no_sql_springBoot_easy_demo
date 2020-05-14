package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.DataGrid;
import com.example.demo.vo.UserListRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2020/1/23.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DataGrid<User> getPath(@PathVariable("id") String id) {

        return userService.getOne(id);
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public DataGrid<User> getParam(@RequestParam("id") String id) {

        return userService.getOne(id);
    }

    @RequestMapping(value = "/one/formdate", method = RequestMethod.GET)
    public DataGrid<User> getFormdate(String id) {

        return userService.getOne(id);
    }

    @RequestMapping(value = "/save_json", method = RequestMethod.POST)
    public DataGrid<String> saveJson(@RequestBody User user) {

        return userService.save(user);
    }

    @RequestMapping(value = "/save_formdata", method = RequestMethod.POST)
    public DataGrid<String> saveFormdata(User user) {

        return userService.save(user);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public DataGrid<String> delete(@PathVariable("id") String id) {

        return userService.deleteOne(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataGrid<String> deleteParam(@RequestParam("id") String id) {

        return userService.deleteOne(id);
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public DataGrid<String> deleteBatch(@RequestParam("listId") List<String> listId) {

        return userService.deleteBatch(listId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public DataGrid<String> update(@RequestBody User user) {

        return userService.update(user);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataGrid<User> list(@RequestBody UserListRequestVo requestVo) {

        return userService.list(requestVo);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public DataGrid<User> drop() {

        return userService.dropUser();
    }

    @RequestMapping(value = "/data/reset", method = RequestMethod.GET)
    public DataGrid<String> reset() {

        return userService.resetDatas();
    }


}
