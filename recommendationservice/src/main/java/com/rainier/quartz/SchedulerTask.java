package com.rainier.quartz;

import com.rainier.service.RecommendationService;
import com.rainier.tool.ConventionalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

/**
 * Created by zhaowanqi on 2018/8/13.
 */
@Component
public class SchedulerTask {
    @Autowired
    private RecommendationService recommendationService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //凌晨1点执行1次0 0 1 * * ?
    //每10秒执行1次0/10 * * * * ?
    //每天16点14执行一次0 14 16 ? * *
    @Scheduled(cron = "0 59 23 * * ?")
    private void process() {
        //统计数据库中已有关键词的相似度(提供定时任务1)
        if(recommendationService.countKeywordSimilarity()) {
            //更新关键词相似度到用户行为推荐表(定时任务2)
           if( recommendationService.updateKeywordSimilarity()){
               //统计所有资源下用户行为并生成模型文件(定时任务3 - 1)
               //偏好度(资源内容相似度总和 * 30 % + 行为总数 * 70 %)
               recommendationService.countResourceSimilarity(ConventionalData.WEIGHT1,ConventionalData.WEIGHT2);
               //统计所有资源下用户行为, 生成模型文件(定时任务3 - 2)
               recommendationService.generateBehaviorCF();
           }
        }
    }
/*    //每6秒执行一次
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("now:"+dateFormat.format(new Date()));
    }*/
}
