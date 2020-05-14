package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.*;
import com.example.demo.vo.UserListRequestVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/1/23.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Override
    public DataGrid<User> list(UserListRequestVo requestVo) {

        List<User> listGetUser = new ArrayList<>();

        PagingDto pagingDto = requestVo.getPagingDto();

        Integer pageSize = pagingDto.getPageSize();

        Integer pageNo = pagingDto.getPageNo();

        Integer start = (pageNo - 1) * pageSize;

        Integer end = pageNo * pageSize;

        List<User> newListUser = CreateDatas.getNewListUser();

        Integer size = newListUser.size();

        if (start > size) {

            return new DataGrid<User>(true, new ArrayList<>());
        }

        if (end > size) {

            newListUser = newListUser.subList(start, size);

        } else {

            newListUser = newListUser.subList(start, end);
        }

        String userId = requestVo.getUserId();

        if (StringUtils.isNotBlank(userId)) {

            List<String> listUserId = newListUser.stream().map(user -> user.getId()).collect(Collectors.toList());

            if (listUserId.contains(userId)) {

                User user = newListUser.stream().filter(user1 -> userId.equals(user1.getId())).findFirst().get();

                if (!ObjectUtils.isEmpty(user)) {

                    listGetUser.add(user);
                }

            } else {

                return new DataGrid<>(false, "当前数据不存在这个userId:" + userId);

            }

        } else {

            listGetUser.addAll(newListUser);
        }

        return new DataGrid<>(true, listGetUser, size);
    }


    @Override
    public DataGrid<User> getOne(String userId) {

        if (StringUtils.isBlank(userId)) {

            return new DataGrid<>(false, "查询一条数据时，用户id不能为空");

        }

//        先判断 userId 是否存在于修改过的数据里
        List<User> updateListUser = CreateDatas.updateListUser;

        User user = null;

        if (ListUtil.isNotEmpty(updateListUser)) {

            user = updateListUser.stream().filter(user1 -> userId.equals(user1.getId())).findFirst().get();

        }

        if (ObjectUtils.isEmpty(user)) {

            user = CreateDatas.list.stream().filter(user1 -> userId.equals(user1.getId())).findFirst().get();

        }

        if (ObjectUtils.isEmpty(user)) {

            return new DataGrid<>(false, "查询一条数据时，用户id不存在");

        }

        return new DataGrid<>(true, user);
    }

    @Override
    public DataGrid dropUser() {

        Map<String, String> map = new LinkedHashMap<>();

        for (User user : CreateDatas.getNewListUser()) {

            map.put(user.getId(), user.getName());
        }
        return new DataGrid(true, map);
    }

    @Override
    public DataGrid<String> update(User user) {

        if (StringUtils.isBlank(user.getId())) {

            return new DataGrid(false, "修改时，用户id不能为空");

        }

        if (StringUtils.isBlank(user.getCode())) {

            return new DataGrid(false, "用户角色不能为空！");

        }

        if (StringUtils.isBlank(user.getName())) {

            return new DataGrid(false, "用户名称不能为空！");

        }

        if (StringUtils.isBlank(user.getPassword())) {

            return new DataGrid(false, "用户密码不能为空！");

        }

        CreateDatas.updateListUser.add(user);

        return new DataGrid(true, "用户修改成功！");

    }

    @Override
    public DataGrid<String> save(User user) {

        if (StringUtils.isBlank(user.getCode())) {

            return new DataGrid(false, "用户角色不能为空！");

        }

        if (StringUtils.isBlank(user.getName())) {

            return new DataGrid(false, "用户名称不能为空！");

        }

        if (StringUtils.isBlank(user.getPassword())) {

            return new DataGrid(false, "用户密码不能为空！");

        }

        user.setId(UUID.randomUUID().toString());

        CreateDatas.list.add(user);

        return new DataGrid(true, "用户保存成功");
    }

    @Override
    public DataGrid<String> deleteOne(String userId) {

        if (StringUtils.isBlank(userId)) {

            return new DataGrid(false, "用户id不能为空");
        }

        List<String> listNewId = CreateDatas.getNewListUser().stream().map(user -> user.getId()).collect(Collectors.toList());

        if (listNewId.contains(userId)) {

            CreateDatas.deleteListId.add(userId);

            return new DataGrid(true, "用户删除成功");

        } else {

            return new DataGrid(true, "当前用户id不存在");
        }

    }

    @Override
    public DataGrid<String> deleteBatch(List<String> listId) {

        if (ListUtil.isEmpty(listId)) {

            return new DataGrid(false, "用户id不能为空");

        }

        List<String> listNewId = CreateDatas.getNewListUser().stream().map(user -> user.getId()).collect(Collectors.toList());

        if (listNewId.containsAll(listId)) {

            CreateDatas.deleteListId.addAll(listId);

            return new DataGrid(true, "用户批量删除成功");

        } else {

            return new DataGrid(false, "有不存在的id，无法批量删除");
        }


    }

    @Override
    public DataGrid<String> resetDatas() {
        CreateDatas.deleteListId = new ArrayList<>();
        CreateDatas.updateListUser = new ArrayList<>();
        CreateDatas.list = new ArrayList<>();
        CreateDatas createDatas = new CreateDatas();
        createDatas.init();
        return new DataGrid(true, "重置数据成功");
    }
}
