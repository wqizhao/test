package com.rainier.service.impl;

import com.github.pagehelper.PageHelper;
import com.rainier.dao.*;
import com.rainier.pojo.*;
import com.rainier.service.MebershipService;
import com.rainier.service.RecommendationService;
import com.rainier.tool.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Barcke on 2018/25.
 */
@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {
    protected static final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);
    @Value("${filePath.uploads}")
    private  String filePath;
    @Value("${fileUrl.uploads}")
    private  String fileUrl;
    @Autowired
    private RecommendDao recommendDao;

    @Autowired
    private ItemKeywordDao itemKeywordDao;

    @Autowired
    private KeywordSimilarityDao keywordSimilarityDao;

    @Autowired
    private MebershipDao mebershipDao;

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private ExpertVideoDao expertVideoDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private MebershipService mebershipService;

    @Override
    public Result list() {

        List<RecommendDo> list = recommendDao.selectAll();

        return new Result("0", "ok", list);
    }

    /**
     * @Title: 记录用户行为, 增加推荐数据
     * @author zhaowanqi
     * @date 2018/8/22
     */
    @Override
    public Result addRecommend(RecommendDo recommendDo) {

        String userUuid = recommendDo.getUserUuid();
        String resourceUuid = recommendDo.getResourceUuid();
        if (recommendDo.getRecommendNum() == null) {
            recommendDo.setRecommendNum(new BigDecimal(1));
        }
        if (recommendDo.getWordSimilarity() == null) {
            recommendDo.setWordSimilarity(new BigDecimal(0));
        }
        if (userUuid == null || resourceUuid == null || recommendDo.getUserId() == null || recommendDo.getResourceId() == null) {
            return new Result("-1", "用户以及资源的id和uuid不能为空", null);
        } else {
            try {
                List<MebershipDo> mebershipDoList = mebershipDao.selectMeberByUuid(userUuid);
                List<RecommendDo> recommendDoList =
                        recommendDao.selectByResoureAndUser(userUuid, resourceUuid);
                if (mebershipDoList.size() > 0) {
                    if (recommendDoList.size() > 0) {
                        RecommendDo recommendDo1 = recommendDoList.get(0);
                        BigDecimal num = recommendDo1.getRecommendNum();
                        recommendDo1.setRecommendNum(recommendDo.getRecommendNum().add(num));
                        recommendDo1.setUpdateTime(new Date());
                        recommendDao.updateByPrimaryKeySelective(recommendDo1);
                    } else {
                        recommendDo.setUpdateTime(new Date());
                        recommendDao.insertSelective(recommendDo);
                    }
                    return Result.success();
                } else {
                    return new Result("-1", "用户不存在", null);
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
                return Result.error();
            }
        }
    }

    /**
     * @Title: 1-解析模型,查询推荐混合资源数据,反馈到前端(基于内容单向推荐给用户)
     * @author zhaowanqi
     * @date 2018/8/23
     */
    public List<RecommendedItem> analyzeItemCF(int userId) throws IOException, TasteException {
        String file = fileUrl + ConventionalData.similarityFileName;
//        String file = "url:"+fileUrl + ConventionalData.similarityFileName;
        //从模型文件中解析出推荐相似度高的内容
        List<RecommendedItem> recommendedItemList =
                AnalyzeRecommendUtil.itemCFRecommender(userId, ConventionalData.RECOMMENDER_NUM, file);
        return recommendedItemList;
    }
    /**
     * @Title: PC端首页推荐新闻
     * @author zhaowanqi
     * @date 2018/8/25
     */
    @Override
    public Result recommendNewsPC(String userUuid) throws IOException, TasteException {
        List<RecommendedItem> recommendedItemList = new ArrayList<>();
        int userId = mebershipService.getMeberDoId(userUuid);
        recommendedItemList = this.analyzeItemCF(userId);
        //首页推荐数量
        int newsNum = ConventionalData.NEWS_NUM;
        //返回到前端的新闻集合
        List<ResourceModel> newsDoList = new ArrayList<ResourceModel>();
        //模型解析的推荐内容id集合
        List<Long> newsIdList = new ArrayList<>();
        if (recommendedItemList.size() > 0) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                newsIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选新闻数据
            newsDoList = newsDao.selectNewsByIds(newsIdList);
            if (newsDoList.size() < newsNum) {
                newsDoList.addAll(newsDao.selectNewsTops(newsNum - newsDoList.size()));
            }
        } else {//没有推荐数据,从数据库中获取最近10条新闻
            newsDoList = newsDao.selectNewsTops(newsNum);
        }
        for (int i = 0; i < newsDoList.size(); i++) {
            ResourceModel resourceModel = newsDoList.get(i);
            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
        }
        return new Result("0", "Success", newsDoList);
    }

    /**
     * @Title: PC端首页推荐帖子
     * @author zhaowanqi
     * @date 2018/8/25
     */
    @Override
    public Result recommendPostsPC(int question,String userUuid) throws IOException, TasteException {
        List<RecommendedItem> recommendedItemList = new ArrayList<>();
        int userId = mebershipService.getMeberDoId(userUuid);
        recommendedItemList = this.analyzeItemCF(userId);
        //首页推荐数量
        int newsNum = ConventionalData.NEWS_NUM;
        //返回到前端的帖子集合
        List<ResourceModel> postsDoList = new ArrayList<ResourceModel>();
        //模型解析的推荐内容id集合
        List<Long> postIdList = new ArrayList<>();
        if (recommendedItemList.size() > 0) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                postIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选新闻数据
            postsDoList = postDao.selectPostsByIds(question,postIdList);
            if (postsDoList.size() < newsNum) {
                postsDoList.addAll(newsDao.selectNewsTops(newsNum - postsDoList.size()));
            }
        } else {//没有推荐数据,从数据库中获取最近10条新闻
            postsDoList = postDao.selectPostsTops(question,newsNum);
        }
        for (int i = 0; i < postsDoList.size(); i++) {
            ResourceModel resourceModel = postsDoList.get(i);
            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
        }
        return new Result("0", "Success", postsDoList);
    }

    /**
     * @Title: PC端首页推荐专家课堂
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @Override
    public Result recommendExpertVideoPC(String userUuid) throws IOException, TasteException {
        List<RecommendedItem> recommendedItemList = new ArrayList<>();
        //返回到前端的专家视频集合
        List<ResourceModel> videoDoList = new ArrayList<ResourceModel>();
        int userId = mebershipService.getMeberDoId(userUuid);
//        if(userId<=0){
////        游客
//        }else {
            recommendedItemList = this.analyzeItemCF(userId);
            //首页推荐数量
            int newsNum = ConventionalData.NEWS_NUM;

            //模型解析的推荐内容id集合
            List<Long> videoIdList = new ArrayList<>();
            if (recommendedItemList.size() > 0) {
                for (int i = 0; i < recommendedItemList.size(); i++) {
                    videoIdList.add(recommendedItemList.get(i).getItemID());
                }
                //筛选新闻数据
                videoDoList = expertVideoDao.selectVideoByIds(videoIdList);
                if (videoDoList.size() < newsNum) {
                    videoDoList.addAll(expertVideoDao.selectVideoTops(newsNum - videoDoList.size()));
                }
            } else {//没有推荐数据,从数据库中获取最近10条专家视频
                videoDoList = expertVideoDao.selectVideoTops(newsNum);
            }
            for (int i = 0; i < videoDoList.size(); i++) {
                ResourceModel resourceModel = videoDoList.get(i);
                resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
                String[] keyWords = videoDoList.get(i).getKeyWord().split("-");
                resourceModel.setKeyWords(keyWords);
                resourceModel.setDurationStr(DateUtil.mSec2hms(resourceModel.getDuration().longValue()));
                System.out.println("时间： " + videoDoList.get(i).getCreateDateStr());
            }
//        }
        return new Result("0", "Success", videoDoList);
    }
    /**
     * @Title: PC端首页推荐关注话题
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @Override
    public Result recommendTopicPC(String userUuid,int pageSize,int pageIndex) throws IOException, TasteException {
        List<RecommendedItem> recommendedItemList = new ArrayList<>();
        //返回到前端的帖子集合
        List<ResourceModel> topicDoList = new ArrayList<ResourceModel>();
        int userId = mebershipService.getMeberDoId(userUuid);
//        if(userId<=0){
////        游客
//        }else {
        recommendedItemList = this.analyzeItemCF(userId);
        //首页推荐数量
        int newsNum = ConventionalData.NEWS_NUM;
        //模型解析的推荐内容id集合
        List<Long> videoIdList = new ArrayList<>();
        if (recommendedItemList.size() > 0) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                videoIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选话题数据
            topicDoList = topicDao.selectTopicByIds(videoIdList);
            if (topicDoList.size() < newsNum) {
                topicDoList.addAll(topicDao.selectTopicTops(newsNum - topicDoList.size()));
            }
        } else {//没有推荐数据,从数据库中获取最近10条专家视频
            topicDoList = topicDao.selectTopicTops(newsNum);
        }
        PageBean<ResourceModel> list = PageUtil.listByPage(topicDoList, new Integer(pageSize), new Integer(pageIndex));
//        for (int i = 0; i < topicDoList.size(); i++) {
//            ResourceModel resourceModel = topicDoList.get(i);
//            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
//            String[] keyWords = topicDoList.get(i).getKeyWord().split("-");
//            resourceModel.setKeyWords(keyWords);
//            resourceModel.setDurationStr(DateUtil.mSec2hms(resourceModel.getDuration().longValue()));
//            System.out.println("时间： " + topicDoList.get(i).getCreateDateStr());
//        }
//        }
        return new Result("0", "Success", list);
    }

    /**
     * @Title: 手机端首页推荐新闻
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @Override
    public Result recommendNewsMT(String userUuid,String recommendNewsMT, int pageIndex, int pageSize) throws IOException, TasteException {
        int userId = mebershipService.getMeberDoId(userUuid);
//        int userID = mebershipService.getMeberDoId(userId);
        List<RecommendedItem> recommendedItemList = new ArrayList<RecommendedItem>();
        recommendedItemList = this.analyzeItemCF(userId);
        List<ResourceModel> newsDoList = new ArrayList<ResourceModel>();
        List<Long> newsIdList = new ArrayList<>();
        if (recommendedItemList.size() > 2) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                newsIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选新闻数据
            newsDoList = newsDao.selectNewsByIds(newsIdList);
        } else {//没有推荐数据,从数据库中获取所有新闻
            newsDoList = newsDao.selectNewsTops(0);
        }
        for (int i = 0; i < newsDoList.size(); i++) {
            ResourceModel resourceModel = newsDoList.get(i);
            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
        }
        PageBean<ResourceModel> list = PageUtil.listByPage(newsDoList, new Integer(pageSize), new Integer(pageIndex));
        return new Result("0", "Success", list);
    }

    /**
     * @Title: 手机端首页推荐帖子
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @Override
    public Result recommendPostsMT(int question,String userUuid, String touristUuid,int pageIndex, int pageSize) throws IOException, TasteException {
        int userID = mebershipService.getMeberDoId(userUuid);
        List<RecommendedItem> recommendedItemList = new ArrayList<RecommendedItem>();
        recommendedItemList = this.analyzeItemCF(userID);
        List<ResourceModel> postDoList = new ArrayList<ResourceModel>();
        List<Long> postIdList = new ArrayList<>();
        if (recommendedItemList.size() > 2) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                postIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选帖子数据
            postDoList = postDao.selectPostsByIds(question,postIdList);
        } else {//没有推荐数据,从数据库中获取所有帖子
            postDoList = postDao.selectPostsTops(question,0);
        }
        for (int i = 0; i < postDoList.size(); i++) {
            ResourceModel resourceModel = postDoList.get(i);
            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
        }
        PageBean<ResourceModel> list = PageUtil.listByPage(postDoList, new Integer(pageSize), new Integer(pageIndex));
        return new Result("0", "Success", list);
    }

    /**
     * @Title: 手机端首页推荐综合资源
     * @author zhaowanqi
     * @date 2018/8/29
     */
    @Override
    public Result recommendResoucresMT(String userUuid, String touristUuid, int pageIndex, int pageSize) throws IOException, TasteException {
        int userID = mebershipService.getMeberDoId(userUuid);
        List<RecommendedItem> recommendedItemList = new ArrayList<RecommendedItem>();
        List<ResourceModel> resourceModelList = new ArrayList<>();
        recommendedItemList = this.analyzeItemCF(userID);
        List<ResourceModel> newsDoList = new ArrayList<ResourceModel>();
        List<Long> newsIdList = new ArrayList<>();
        if (recommendedItemList.size() > 2) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                newsIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选新闻数据
            newsDoList = newsDao.selectNewsByIds(newsIdList);
        } else {//没有推荐数据,从数据库中获取所有新闻
            newsDoList = newsDao.selectNewsTops(10);
        }
        List<ResourceModel> postDoList = new ArrayList<ResourceModel>();
        List<Long> postIdList = new ArrayList<>();
        if (recommendedItemList.size() > 2) {
            for (int i = 0; i < recommendedItemList.size(); i++) {
                postIdList.add(recommendedItemList.get(i).getItemID());
            }
            //筛选新闻数据
            postDoList = postDao.selectPostsByIds(2,postIdList);
        } else {//没有推荐数据,从数据库中获取所有新闻
            postDoList = postDao.selectPostsTops(2,10);
        }
        newsDoList.addAll(postDoList);
        for (int i = 0; i < newsDoList.size(); i++) {
            ResourceModel resourceModel = newsDoList.get(i);
            resourceModel.setCreateDateStr(DateUtil.dateToUTFStr(resourceModel.getCreateDate()));
        }
        Collections.shuffle(newsDoList);
        PageBean<ResourceModel> list = PageUtil.listByPage(newsDoList, new Integer(pageSize), new Integer(pageIndex));
        return new Result("0", "Success", list);
    }

    /**
     * @Title: 统计数据库中已有关键词的相似度(提供定时任务1)
     * @author zhaowanqi
     * @date 2018/8/16
     */
    @Override
    public Boolean countKeywordSimilarity() {
        Boolean flag = false;
        keywordSimilarityDao.truncate();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            List<ItemKeywordDo> list = itemKeywordDao.getItemKeywordList(new Long(4), new Long(3));
            List<KeywordSimilarityDo> listSim = new ArrayList<>();
            logger.info("开始计算： " + dateFormat.format(new Date()) + "返回数量:" + list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                BigDecimal counts = new BigDecimal(0);
                ItemKeywordDo itemKeywordDo = (ItemKeywordDo) list.get(i);
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        counts = counts.add(new BigDecimal(SimFeatureUtil.sim(itemKeywordDo.getKeyword(),
                                list.get(j).getKeyword())));
                    }
                }
                KeywordSimilarityDo keywordSimilarityDo = new KeywordSimilarityDo();
                keywordSimilarityDo.setItemUuid(itemKeywordDo.getItemUuid());
                keywordSimilarityDo.setKeyWord(itemKeywordDo.getKeyword());
                keywordSimilarityDo.setCountKeySimilarity(counts.multiply(new BigDecimal(itemKeywordDo.getTf())));
                keywordSimilarityDo.setUpdateTime(new Date());
                keywordSimilarityDao.insertSelective(keywordSimilarityDo);
            }
            flag = true;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        } finally {
            return true;
        }
    }

    /**
     * @Title: 更新关键词相似度到用户行为推荐表(定时任务2)
     * @author zhaowanqi
     * @date 2018/8/22
     */
    @Override
    public Boolean updateKeywordSimilarity() {
        Boolean flag = false;
        try {
            List<RecommendDo> recommendDoList = recommendDao.selectAll();
            for (int i = 0; i < recommendDoList.size(); i++) {
                RecommendDo recommendDo = recommendDoList.get(i);
                Map<String, BigDecimal> map = keywordSimilarityDao.getListByItemUuid(recommendDo.getResourceUuid());
                if (map != null) {
                    recommendDo.setWordSimilarity(map.get("countNum"));
                    recommendDao.updateByPrimaryKeySelective(recommendDo);
                } else {
                    continue;
                }
            }
            flag = true;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        } finally {
            return true;
        }
    }
    /**
     * @Title: 统计所有资源下用户行为并生成模型文件(定时任务3 - 1)
     * 偏好度(资源内容相似度总和 * 30 % + 行为总数 * 70 %)
     * @author zhaowanqi
     * @date 2018/8/17
     */
    @Override
    public Result countResourceSimilarity(double weight1, double weight2) {
        //统计资源
        List<RecommendDo> recommendDoList = recommendDao.selectAll();
        List<KeywordSimilarityModel> keywordSimilarityModelList = new ArrayList<>();
        for (int i = 0; i < recommendDoList.size(); i++) {
            RecommendDo recommendDo = recommendDoList.get(i);
            KeywordSimilarityModel keywordSimilarityModel = new KeywordSimilarityModel();
            keywordSimilarityModel.setUserId(recommendDo.getUserId());
            keywordSimilarityModel.setItemId(recommendDo.getResourceId());
            BigDecimal likeNum1 = recommendDo.getRecommendNum().multiply(new BigDecimal(weight1));
            BigDecimal likeNum2 = recommendDo.getWordSimilarity().multiply(new BigDecimal(weight2));
            keywordSimilarityModel.setLikeNum(likeNum1.add(likeNum2));
            keywordSimilarityModelList.add(keywordSimilarityModel);
        }
        //生成模型
        String pathName = filePath + "similarity";
        GenerateRecommendCSV.setFile(keywordSimilarityModelList, pathName);
        return Result.success();
    }

    /**
     * @Title: 统计所有资源下用户行为, 生成模型文件(定时任务3 - 2)
     * 偏好度(行为)
     * @author zhaowanqi
     * @date 2018/8/17
     */
    @Override
    public Result generateBehaviorCF() {
        //用户对内容相似度统计 userId 内容Id 感兴趣度
        List<KeywordSimilarityModel> keywordSimilarityModelList = new ArrayList<>();
        List<RecommendDo> recommendDoList = recommendDao.selectAll();
        for (int i = 0; i < recommendDoList.size(); i++) {
            RecommendDo recommendDo = recommendDoList.get(i);
            KeywordSimilarityModel keywordSimilarityModel = new KeywordSimilarityModel();
            keywordSimilarityModel.setUserId(recommendDo.getUserId());
            keywordSimilarityModel.setItemId(recommendDo.getResourceId());
            keywordSimilarityModel.setLikeNum(recommendDo.getRecommendNum());
            keywordSimilarityModelList.add(keywordSimilarityModel);
        }
        //生成模型
        String pathName = filePath + "behavior";
        GenerateRecommendCSV.setFile(keywordSimilarityModelList, pathName);
        return Result.success();
    }
}
