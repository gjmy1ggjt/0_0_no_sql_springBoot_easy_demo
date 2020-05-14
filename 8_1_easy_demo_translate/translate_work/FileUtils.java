package com.example.demo.translate_work;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/28.
 */
public class FileUtils {

    public static List<File> listFile = new ArrayList<>();

    public static List<Map<String, List<Map<String, List<String>>>>> listDatas = new ArrayList<>();

    public static String canonicalPath() {

        String courseFile = "";

        try {
            File directory = new File("");//参数为空

            courseFile = directory.getCanonicalPath();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseFile;
    }

    public static String thisFileClassPath(Class clazz) {

        return clazz.getResource("").toString();
    }

    public static String getClassName(String fileName) {
//  UserController
        return fileName.substring(fileName.lastIndexOf(Constant.step_right_two) + 1, fileName.lastIndexOf("."));

    }

    public static String getClassDirName(String fileName) {
//        src
        return fileName.substring(fileName.lastIndexOf(Constant.step_right_two) + 1, fileName.length());

    }

    public static String getClassFileName(String fileName) {
//  UserController.class
        return fileName.substring(fileName.lastIndexOf(Constant.step_right_two) + 1, fileName.length());

    }

    public static String getClassFileLastName(String fileName) {
//UserController.class => class

        String[] arr = fileName.split(Constant.point);

        if (arr != null && arr.length == 2) {

            return arr[1];

        } else {

            return fileName;
        }
    }

    public static List<File> getFiles(String filePath) {

        listFile = new ArrayList<>();

        File root = new File(filePath);

        File[] files = root.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {

                if (Constant.getDirListName().contains(file.getName())) {
//                  controller 里面也存在文件夹不过只有一层
                    if (file.isDirectory()) {

                        getListFile(file.getAbsolutePath());

                    }
                }
            } else {

                String className = getClassFileName(file.getName());

                if (Constant.javaType.equals(getClassFileLastName(className))) {

                    listFile.add(file);
                }
//                System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
            }
        }

        return listFile;
    }

    public static List<File> getListFile(String filePath) {

        File rootClild = new File(filePath);

        File[] filesClild = rootClild.listFiles();

        for (File fileClild : filesClild) {

            String fileClildName = fileClild.getName();

            if (fileClild.isDirectory()) {

                getListFile(fileClild.getAbsolutePath());

            } else {

                String className = getClassFileName(fileClildName);

                if (Constant.javaType.equals(getClassFileLastName(className))) {

                    listFile.add(fileClild);
                }
            }
        }
        return listFile;
    }

    public static List<String> readListFile(File file) {

        List<String> listString = new ArrayList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));

            String line = null;

            while ((line = br.readLine()) != null) {

                String useLine = IgnoreStringUtils.noIgnoreStringTwo(line);

                if (StringUtils.isNotBlank(useLine)) {

                    if (StringUtils.hasChinese(useLine) && !IgnoreStringUtils.ignoreStringLine(useLine)) {

                        listString.add(useLine);
                    }
                }
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();

        } finally

        {
            try {

                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listString;
    }

    public static void writeListDatas(List<Map<String, List<Map<String, List<String>>>>> listDatas) {


    }

    public static void writeListString(List<String> list) {

        BufferedWriter bi = null;
        try {
            File file = new File(canonicalPath() + "\\test.txt");

            bi = new BufferedWriter(new FileWriter(file));

            for (String str : list) {

                bi.write(str.trim());

                bi.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printFile(List<File> list) {

        for (File file : list) {

            System.out.println(file.getName());
        }
    }

//    java -classpath ****.jar com.example.demo.translate_work.FileUtils [args]
    public static void main(String[] args) {

//        System.out.println(canonicalPath());

        getFiles(canonicalPath());

        List<String> listString = new ArrayList<>();

        for (File file : listFile) {

            List<String> list = readListFile(file);

            if (ListUtils.isNotEmpty(list)) {

                listString.addAll(list);
            }
        }
        writeListString(listString);

    }


}
