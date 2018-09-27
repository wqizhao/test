package com.rainier.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "recommend")
public class RecommendDo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    @GeneratedValue(generator="UUID")
    private String uuid;

    private Integer userId;

    private String userUuid;

    private Integer resourceId;

    private String resourceUuid;

    private Integer resourceType;

    private BigDecimal recommendNum;

    private BigDecimal wordSimilarity;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUuid() {
        return resourceUuid;
    }

    public void setResourceUuid(String resourceUuid) {
        this.resourceUuid = resourceUuid;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public BigDecimal getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(BigDecimal recommendNum) {
        this.recommendNum = recommendNum;
    }

    public BigDecimal getWordSimilarity() {
        return wordSimilarity;
    }

    public void setWordSimilarity(BigDecimal wordSimilarity) {
        this.wordSimilarity = wordSimilarity;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}