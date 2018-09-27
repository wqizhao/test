package com.rainier.pojo;

import java.math.BigDecimal;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: KeywordSimilarityModel
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2019:39
 */
public class KeywordSimilarityModel {

    private Integer userId;
    private Integer itemId;
    private BigDecimal likeNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(BigDecimal likeNum) {
        this.likeNum = likeNum;
    }
}
