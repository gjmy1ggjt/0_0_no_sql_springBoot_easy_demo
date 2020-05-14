package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.utils.DataGrid;
import com.example.demo.vo.UserListRequestVo;

import java.util.List;

/**
 * Created by Administrator on 2020/1/23.
 */
public interface UserService {

    DataGrid<User> list(UserListRequestVo requestVo);

    DataGrid<User> getOne(String userId);

    DataGrid dropUser();

    DataGrid<String> update(User user);

    DataGrid<String> save(User user);

    DataGrid<String> deleteOne(String userId);

    DataGrid<String> deleteBatch(List<String> listId);

    DataGrid<String> resetDatas();
}
