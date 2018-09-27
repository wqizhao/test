package com.rainier;

import com.rainier.pojo.ItemKeywordDo;
import com.rainier.service.RecommendationService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;
import java.util.*;
/**
 * Created by Barcke on 2018/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class,Test.class})
public class Test {
    final static int NEIGHBORHOOD_NUM = (int)((Math.random()+2)*10);//临近的用户个数
    final static int RECOMMENDER_NUM= 3;//推荐物品的最大个数
    @Autowired
    private RecommendationService recommendationService;


/**
 * @Title: 基于用户推荐,列出数据模型下所有用户的推荐
 * @author zhaowanqi
 * @date 2018/8/15
 */
    @org.junit.Test
    public void testUserCF()throws IOException, TasteException  {
        String file = "d:\\test.csv";
        //构造数据模型
        DataModel model = new FileDataModel(new File(file));
        //用PearsonCorrelation 算法计算用户相似度
        UserSimilarity user = new EuclideanDistanceSimilarity(model);
        //计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
        //用户近邻算法
        Recommender r = new GenericUserBasedRecommender(model, neighbor, user);//用户推荐算法
        LongPrimitiveIterator iter = model.getUserIDs();///得到用户ID

        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);//得到推荐的结果，RECOMMENDER_NUM是推荐的数目
            System.out.printf("uid:%s", uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
            }
            System.out.println();
        }
    }

/**
 * @Title: 基于内容推荐(所有用户的内容推荐)
 * @author zhaowanqi
 * @date 2018/8/15
 */
    @org.junit.Test
    public void testItemCF ()throws IOException, TasteException  {
        String file = "d:\\file\\test.csv";
        DataModel model = new FileDataModel(new File(file));//数据模型
        ItemSimilarity item=new EuclideanDistanceSimilarity(model);//用户相识度算法
        Recommender r=new GenericItemBasedRecommender(model,item);//物品推荐算法
        LongPrimitiveIterator iter =model.getUserIDs();
        while(iter.hasNext()){
            long uid=iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
//            System.out.printf("uid:%s",uid);
            for (RecommendedItem ritem : list) {
                System.out.println("user: "+uid+" itemID: "+ ritem.getItemID()+" value:"+ ritem.getValue());
            }
        }
    }
    @org.junit.Test
    public void countResourceSimilarity ()throws IOException, TasteException  {
        recommendationService.countResourceSimilarity(0.3,0.7);
    }
    @Value("${filePath.uploads}")
    private  String filePath;
    @Value("${fileUrl.uploads}")
    private  String fileUrl;
    @org.junit.Test
    public void testPath ()throws IOException, TasteException  {
        System.out.println(filePath+fileUrl);
    }
}
