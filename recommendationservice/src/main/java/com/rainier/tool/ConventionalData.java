package com.rainier.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;

import static org.springframework.util.ResourceUtils.getURL;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: ConventionalData
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2414:29
 */
public class ConventionalData {

//    @Value("${filePath.uploads}")
//    public static String filePath;
//    @Value("${fileUrl.uploads}")
//    public static String fileUrl;

    //本地测试路径
//    public static String path = "D:\\file\\";
    public static String similarityFileName ="similarity";
    public static String behaviorFileName = "behavior";
//    public static File pathFile;
//
//    static {
//        try {
//            pathFile = new File(getURL("classpath:").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    //测试路径
//     public static String path = pathFile.getParentFile().getParentFile().getParent() + File.separator + "uploads" + File.separator;
    //上线路径
//    public static String onlinePath = pathFile.getParentFile().getParentFile().getParent() + File.separator + "uploads" + File.separator;
    // public static   String onlinePaths=onlinePath.substring(5,onlinePath.length());
    public  static  int NEIGHBORHOOD_NUM = 10;
    public  static  int RECOMMENDER_NUM = 10;
    //PC端首页新闻推荐数量
    public  static  int NEWS_NUM = 10;
    //PC端首页农技专家推荐数量
    public  static  int NJ_Export_NUM = 10;
    //偏好度权重(资源内容相似度总和占30 % ,行为总数占70 %)
    public static double WEIGHT1 = 0.3;
    public static double WEIGHT2 = 0.7;



}
