package com.rainier.tool;

import com.rainier.pojo.KeywordSimilarityModel;
import com.rainier.pojo.RecommendDo;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.List;

import static org.springframework.util.ResourceUtils.getURL;

/**
 * Created by zhaowanqi on 2018/8/25
 */
public class GenerateRecommendCSV {

    public static String filenameTemp;
    @Value("${filePath.uploads}")
    private  String filePath;
    @Value("${fileUrl.uploads}")
    private  String fileUrl;


    public static void setFile(List<KeywordSimilarityModel> dataList, String fileName) {
//        fileName = "data5";
        try {
            //清空文件内的内容
//            RecommendTxtOperate.clearInfoForFile(fileName);
            GenerateRecommendCSV.creatTxtFile(fileName);
            for (int i = 0; i < dataList.size(); i++) {
                KeywordSimilarityModel keywordSimilarityModel = dataList.get(i);
                String sbr = keywordSimilarityModel.getUserId() + "," + keywordSimilarityModel.getItemId() + "," + keywordSimilarityModel.getLikeNum().doubleValue();
                GenerateRecommendCSV.writeTxtFile(sbr);
            }
//        TxtOperate.delFolder(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static boolean creatTxtFile(String fileName) throws IOException {

        boolean flag = false;
        filenameTemp =  fileName + ".csv";
        File filename = new File(filenameTemp);
        File fileParent = filename.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }else{
            clearInfoForFile(fileName);
        }
        return flag;
    }

    /**
     * 写文件
     *
     * @param newStr 新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    /**
     * 清空文件内容
     *
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        filenameTemp =  fileName + ".csv";
        File file = new File(filenameTemp);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public  boolean deleteFile(String fileName) {
//        filenameTemp = "url:"+fileUrl + fileName + ".csv";
        filenameTemp = filePath + fileName + ".csv";

        File file = new File(filenameTemp);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param path 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//删除文件夹里面的文件
//                delFolder(path + "/" + tempList[i]);//删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @Title: 删除文件夹
     * @author zhaowanqi
     * @date 2018/8/17
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

