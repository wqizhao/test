package com.rainier.service;

import com.rainier.pojo.NewsDo;
import com.rainier.pojo.RecommendDo;
import com.rainier.tool.Result;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Barcke on 2018/7/30.
 */
public interface RecommendationService {
    /**
     * @Title: 记录用户行为(对外服务)
     * @author zhaowanqi
     * @date 2018/8/24
     */
    Result addRecommend(RecommendDo recommendDo);

    /**
     * @Title: 查询所有推荐信息
     * @author zhaowanqi
     * @date 2018/8/24
     */
    Result list();

    /**
     * @Title: 1-解析模型,查询推荐混合资源数据,反馈到前端(基于内容单向推荐给用户)
     * @author zhaowanqi
     * @date 2018/8/25
     */
    List<RecommendedItem> analyzeItemCF(int userId) throws IOException, TasteException;

    /**
     * @Title: PC端首页推荐新闻
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendNewsPC(String userUuid) throws IOException, TasteException;

    /**
     * @Title: PC端首页推荐帖子
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendPostsPC(int question,String userUuid) throws IOException, TasteException;

    /**
     * @Title: PC端首页推荐专家课堂
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendExpertVideoPC(String userUuid) throws IOException, TasteException;
    /**
     * @Title: PC端首页推荐关注话题
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendTopicPC(String userUuid,int pageSize,int pageIndex) throws IOException, TasteException;

    /**
     * @Title: 手机端推荐新闻
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendNewsMT(String userUuid,String touristUuid, int pageIndex, int pageSize) throws IOException, TasteException;

    /**
     * @Title: 手机端推荐帖子
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendPostsMT(int question,String userUuid,String touristUuid,int pageIndex, int pageSize) throws IOException, TasteException;

    /**
     * @Title: 手机端推荐综合资源
     * @author zhaowanqi
     * @date 2018/8/25
     */
    Result recommendResoucresMT(String userUuid, String touristUuid,int pageIndex, int pageSize) throws IOException, TasteException;

    /**
     * @Title: 统计关键词相似度(提供定时任务1)
     * @author zhaowanqi
     * @date 2018/8/24
     */
    Boolean countKeywordSimilarity();

    /**
     * @Title: 更新关键词相似度到用户行为推荐表(定时任务2)
     * @author zhaowanqi
     * @date 2018/8/24
     */
    Boolean updateKeywordSimilarity();

    /**
     * @Title: 统计所有资源下用户行为并生成模型文件(定时任务3 - 1)
     * 偏好度(资源内容相似度总和 * 30 % + 行为总数 * 70 %)
     * @author zhaowanqi
     * @date 2018/8/16
     */
    Result countResourceSimilarity(double weight1, double weight2);

    /**
     * @Title: 统计所有资源下用户行为, 生成模型文件(定时任务3 - 2)
     * @author zhaowanqi
     * @date 2018/8/16
     */
    Result generateBehaviorCF();
}
