package com.rainier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rainier.pojo.RecommendDo;
import com.rainier.service.LBSInfoService;
import com.rainier.service.RecommendationService;
import com.rainier.tool.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Barcke on 2018/7/30.
 */
@RestController
@RequestMapping("/Recommendation")
@Api("RecommendationController")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private LBSInfoService lbsInfoService;

    /**
     * @Description: 用户行为推荐接口
     * 资源类型:1、资讯 2、书籍 3、期刊 4、视频 5、社区
     * @author zhaowanqi
     * @date 2018/8/22
     */
    @ApiOperation(value = "记录用户行为", notes = "记录用户行为")
    @RequestMapping(value = "/addRecommend")
    @ResponseBody
    public Result addRecommend(@ApiParam("资源类型(resourceType):1、资讯 2、书籍 3、期刊 4、视频 5、社区") @RequestBody RecommendDo recommendDo) {
        return recommendationService.addRecommend(recommendDo);
    }

    /**
     * @Title: 推荐新闻接口-PC端
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "PC端推荐新闻接口", notes = "PC端推荐新闻接口")
    @RequestMapping(value = "/recommendNewsPC")
    @ResponseBody
    public Result recommendNewsPC(HttpServletRequest request,
                                  @RequestParam(defaultValue = "1") int pageIndex,
                                  @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
//            String userUuid = jsonObject.get("userUuid").toString();
            String userUuid = request.getSession().getAttribute("userUuid").toString();
//             userUuid = "493e690534a64e858826342ca94cba54";
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            //测试环境模拟从session中获取用户id
//        String userUuid = "c63c0d9ab1374607b0fade38500d0fd8";
            return recommendationService.recommendNewsPC(userUuid);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 推荐帖子接口-PC端
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "PC端推荐帖子接口", notes = "PC端推荐帖子接口")
    @RequestMapping(value = "/recommendPostsPC")
    @ResponseBody
    public Result recommendPostsPC(HttpServletRequest request,
                                   @RequestParam(defaultValue = "0") int question,
                                   @RequestParam(defaultValue = "1") int pageIndex,
                                   @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//        Result result = commonService.getSession();
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
//        String userUuid = jsonObject.get("userUuid").toString();
            String userUuid = request.getSession().getAttribute("userUuid").toString();
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            //测试环境模拟从session中获取用户id
//        String userUuid = "c63c0d9ab1374607b0fade38500d0fd8";
            return recommendationService.recommendPostsPC(question,userUuid);
        } catch (Exception e) {
            return Result.error();
        }
    }
    /**
     * @Title: 推荐作者其他文章-PC端
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "PC端推荐作者其他文章接口", notes = "PC端推荐作者其他文章接口")
    @RequestMapping(value = "/recommendPostsByuserUUidPC")
    @ResponseBody
    public Result recommendPostsByuserUUidPC(HttpServletRequest request,
                                   @RequestParam(defaultValue = "0") int question,
                                   @RequestParam(value = "userUuid",defaultValue = "null") String userUuid,
                                   @RequestParam(value = "pageIndex",defaultValue = "1") int pageIndex,
                                   @RequestParam(value = "pageSize",defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "未传作者uuid");
            }
            return recommendationService.recommendPostsPC(question,userUuid);
        } catch (Exception e) {
            return Result.error();
        }
    }
    /**
     * @Title: 推荐视频接口-PC端
     * @author zhaowanqi
     * @date 2018/8/15
     */

    @ApiOperation(value = "PC端推荐视频接口", notes = "PC端推荐视频接口")
    @RequestMapping(value = "/recommendExpertVideoPC", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result recommendExpertVideoPC(HttpServletRequest request,
                                         @RequestParam(defaultValue = "1") int pageIndex,
                                         @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
//            String userUuid = jsonObject.get("userUuid").toString();
            String userUuid = request.getSession().getAttribute("userUuid").toString();
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            //测试环境模拟从session中获取用户id
//        String userUuid = "c63c0d9ab1374607b0fade38500d0fd8";
            return recommendationService.recommendExpertVideoPC(userUuid);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 推荐关注话题-PC端
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "PC端推荐关注话题", notes = "PC端推荐关注话题")
    @RequestMapping(value = "/recommendTopicPC", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result recommendTopicPC(HttpServletRequest request,
                                   @RequestParam(defaultValue = "1") int pageIndex,
                                   @RequestParam(defaultValue = "10") int pageSize) throws IOException, TasteException {
        try {
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
//            String userUuid = jsonObject.get("userUuid").toString();
            String userUuid = request.getSession().getAttribute("userUuid").toString();
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            //测试环境模拟从session中获取用户id
//        String userUuid = "c63c0d9ab1374607b0fade38500d0fd8";
            return recommendationService.recommendTopicPC(userUuid, pageSize, pageIndex);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 推荐新闻接口-手机端
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "手机端推荐新闻接口", notes = "手机端推荐新闻接口")
    @RequestMapping(value = "/recommendNewsMT")
    public Result recommendNewsMT(HttpServletRequest request,
                                  @RequestParam(defaultValue = "0") String userUuid,
                                  @RequestParam(defaultValue = "0") String touristUuid,
                                  @RequestParam(defaultValue = "1") int pageIndex,
                                  @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            if (userUuid.equals("0")) {
                userUuid = request.getSession().getAttribute("userUuid").toString();
//                userUuid = jsonObject.get("userUuid").toString();
            }
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            return recommendationService.recommendNewsMT(userUuid, touristUuid, pageIndex, pageSize);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 手机端推荐帖子接口
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @ApiOperation(value = "手机端推荐帖子接口", notes = "手机端推荐帖子接口")
    @RequestMapping(value = "/recommendPostsMT")
    public Result recommendPostsMT(HttpServletRequest request,
                                   @RequestParam(defaultValue = "1") int question,
                                   @RequestParam(defaultValue = "0") String userUuid,
                                   @RequestParam(defaultValue = "0") String touristUuid,
                                   @RequestParam(defaultValue = "1") int pageIndex,
                                   @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//            userUuid ="c63c0d9ab1374607b0fade38500d0fd8";
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            if (userUuid.equals("0")) {
                userUuid = request.getSession().getAttribute("userUuid").toString();
//                userUuid = jsonObject.get("userUuid").toString();
            }
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            return recommendationService.recommendPostsMT(question,userUuid, touristUuid, pageIndex, pageSize);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 推荐综合资源接口-手机端
     * @author zhaowanqi
     * @date 2018/8/28
     */
    @ApiOperation(value = "推荐综合资源接口", notes = "推荐综合资源接口")
    @RequestMapping(value = "/recommendResoucresMT")
    public Result recommendResoucresMT(HttpServletRequest request,
                                       @RequestParam(defaultValue = "0") String userUuid,
                                       @RequestParam(defaultValue = "0") String touristUuid,
                                       @RequestParam(defaultValue = "1") int pageIndex,
                                       @RequestParam(defaultValue = "6") int pageSize) throws IOException, TasteException {
        try {
//            Result result = commonService.getSession();
//            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            if (userUuid.equals("0")) {
                userUuid = request.getSession().getAttribute("userUuid").toString();
//                userUuid = jsonObject.get("userUuid").toString();
            }
            //正式环境打开
            if (userUuid == null || userUuid == "" || userUuid == "null") {
                return new Result("-2", "Error", "用户登录失效,请重新登录");
            }
            return recommendationService.recommendResoucresMT(userUuid, touristUuid, pageIndex, pageSize);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @Title: 推荐精品图书接口
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "推荐精品图书接口", notes = "推荐精品图书接口")
    @RequestMapping(value = "/recommendBooks")
    @ResponseBody
    public Result recommendBooks() {
        return recommendationService.list();
    }

    /**
     * @Title: 推荐问答接口
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "推荐问答接口", notes = "推荐民间高手智慧")
    @RequestMapping(value = "/recommendPosts")
    @ResponseBody
    public Result recommendPosts() {
        return recommendationService.list();
    }

    /**
     * @Title: 推荐关心主题接口
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "推荐关心主题接口", notes = "推荐关心主题接口")
    @RequestMapping(value = "/recommendTopics")
    @ResponseBody
    public Result recommendTopics() {
        return recommendationService.list();
    }

    /**
     * @Title: 推荐周围专家接口
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "推荐周围专家接口", notes = "推荐周围专家接口")
    @RequestMapping(value = "/recommendLbsExperts")
    @ResponseBody
    public Result recommendLbsExperts() {
        return recommendationService.list();
    }

    /**
     * @Title: 推荐与你相关的专家接口
     * @author zhaowanqi
     * @date 2018/8/15
     */
    @ApiOperation(value = "推荐与你相关的专家接口", notes = "推荐与你相关的专家接口")
    @RequestMapping(value = "/recommendExperts")
    @ResponseBody
    public Result recommendExperts() {
        return recommendationService.list();
    }

    /**
     * @Title: redis session共享(写入session)
     * @author zhaowanqi
     * @date 2018/8/23
     */
    @ApiOperation(value = "写入session", notes = "写入session")
    @RequestMapping(value = "/setSession")
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("name", "瑞尔智讯");
        map.put("message", "将name=瑞尔智讯写入到session中");
        return map;
    }

    /**
     * @Title: redis session共享(获取session)
     * @author zhaowanqi
     * @date 2018/8/23
     */
    @ApiOperation(value = "获取session", notes = "获取session")
    @RequestMapping(value = "/getSession")
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", "从session中获取name:" + request.getSession().getAttribute("name"));
        return map;
    }

    /**
     * @Description: 基于地理位置推荐专家
     * @author zhaowanqi
     * @date 2018/8/22
     */
    @RequestMapping(value = "/recommendExportLBSPC")
    @ResponseBody
    public Result recommendExportLBSPC(HttpServletRequest request) throws IOException, TasteException {

        return new Result("0", "Success", lbsInfoService.getSimLBSExport(request));
    }

}
