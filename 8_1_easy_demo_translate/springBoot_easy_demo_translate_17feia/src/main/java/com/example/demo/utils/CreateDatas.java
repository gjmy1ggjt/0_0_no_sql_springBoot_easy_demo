package com.example.demo.utils;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/1/23.
 */

@Component
public class CreateDatas {

    private String[] names = new String[]{"害羞", "小媳妇", "大老婆", "丫鬟", "婢女", "大良心", "恶婆娘", "小可爱", "乖宝宝", "亲爱的"};

    public static List<User> list = new ArrayList<>();

    public static List<String> deleteListId = new ArrayList<>();

    public static List<User> updateListUser = new ArrayList<>();

//    public static List<User> saveListUser = new ArrayList<>();

    @PostConstruct
    public void init() {

        if (ListUtil.isEmpty(list)) {

            for (int i = 0; i < 100; i++) {

                User user = new User();

                user.setId(UUID.randomUUID().toString());

                user.setCode(DateUtil.formatSeventhToday());

                user.setName(names[i / 10] + "_" + (i + 1));

                user.setPassword("5201314");

                list.add(user);

            }
        }
    }


    public static List<User> getNewListUser() {

        List<User> newListUser = new ArrayList<>();

//        CreateDatas.list.addAll(CreateDatas.saveListUser);

        if (ListUtil.isNotEmpty(CreateDatas.list)) {

            for (User user : CreateDatas.list) {

                String userId = user.getId();

                if (!CreateDatas.deleteListId.contains(userId)) {

                    newListUser.add(user);
                }

                if (ListUtil.isNotEmpty(CreateDatas.updateListUser)) {

                    List<String> updateListUserId = CreateDatas.updateListUser.stream().map(user1 -> user1.getId()).collect(Collectors.toList());

                    if (updateListUserId.contains(userId)) {

                        User updateUser = CreateDatas.updateListUser.stream().filter(user2 -> user2.getId().equals(userId)).findFirst().get();

                        newListUser.add(updateUser);
                    }
                }
            }
        }

        return newListUser;
    }

    public static void main(String[] args) {
        getNewListUser();
        System.out.println(getNewListUser().toArray().toString());
    }
}
