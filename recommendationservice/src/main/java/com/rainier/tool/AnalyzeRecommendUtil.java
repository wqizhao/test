package com.rainier.tool;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: RecommendUtil
 * @Description: 数据模型分析
 * @Modified qdzwq
 * @date 2018/8/25 19:31
 */
public class AnalyzeRecommendUtil {
    /**
     * @Title: 基于用户推荐,单用户推荐
     * @author zhaowanqi
     * @date 2018/8/16
     */
    public static List<RecommendedItem> userCFRecommender(int NEIGHBORHOOD_NUM,int RECOMMENDER_NUM,int userId ,String pathname) throws IOException, TasteException {
        // step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
        List<RecommendedItem> recommendations = null;
        try {
            DataModel model = new FileDataModel(new File(pathname+".csv"));//构造数据模型，Database-based
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//用PearsonCorrelation 算法计算用户相似度
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, model);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
            Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, similarity));//构造推荐引擎，采用 CachingRecommender 为 RecommendationItem 进行缓存
            recommendations = recommender.recommend(userId, RECOMMENDER_NUM);//得到推荐的结果，RECOMMENDER_NUM是推荐的数目
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return recommendations;
    }

    /**
     * @Title: 基于内容推荐,用户单向推荐
     * @author zhaowanqi
     * @date 2018/8/24
     */
    public static List<RecommendedItem>  itemCFRecommender (int userId,int RECOMMENDER_NUM,String pathName)throws IOException, TasteException {
        List<RecommendedItem> recommendedItemList = new ArrayList<RecommendedItem>();
        try {
            System.out.println("path:"+pathName);
            File url =  new File(pathName+".csv");
            DataModel model = new FileDataModel(url);//构造数据模型，File-based
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度
            Recommender recommender = new GenericItemBasedRecommender(model, similarity);//构造推荐引擎
            recommendedItemList = recommender.recommend(userId, RECOMMENDER_NUM);//得到推荐接过
        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
            return recommendedItemList;
        }
        return recommendedItemList;
    }
    /**
     * @Title: 基于用户推荐,所有用户推荐
     * @author zhaowanqi
     * @date 2018/8/16
     */
    public static void usersCFRecommender(int NEIGHBORHOOD_NUM,int RECOMMENDER_NUM,int userId ,String pathname) throws IOException, TasteException {
        // step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
//        String file = "d:\\test.csv";
        //构造数据模型
        DataModel model = new FileDataModel(new File(pathname+".csv"));
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
     * @Title: 基于内容推荐,汇总所有用户的内容推荐
     * @author zhaowanqi
     * @date 2018/8/24
     */
    public static void itemsCF (int RECOMMENDER_NUM,String file)throws IOException, TasteException  {
        DataModel model = new FileDataModel(new File(file+".csv"));//数据模型
        ItemSimilarity item=new EuclideanDistanceSimilarity(model);//用户相识度算法
        Recommender r=new GenericItemBasedRecommender(model,item);//物品推荐算法
        LongPrimitiveIterator iter =model.getUserIDs();
        while(iter.hasNext()){
            long uid=iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            System.out.printf("uid:%s",uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
            }
        }
    }
}
