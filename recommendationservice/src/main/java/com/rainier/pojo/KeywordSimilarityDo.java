package com.rainier.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "keyword_similarity")
public class KeywordSimilarityDo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String keyWord;

    private Date updateTime;

    @GeneratedValue(generator="UUID")
    private String uuid;

    private String itemUuid;

    private BigDecimal countKeySimilarity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid == null ? null : itemUuid.trim();
    }

    public BigDecimal getCountKeySimilarity() {
        return countKeySimilarity;
    }

    public void setCountKeySimilarity(BigDecimal countKeySimilarity) {
        this.countKeySimilarity = countKeySimilarity;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}