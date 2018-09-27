package com.rainier.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item_keyword")
public class ItemKeywordDo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    @GeneratedValue(generator="UUID")
    private String uuid;

    private String itemUuid;

    private String keyword;

    private Integer tf;

    private BigDecimal idf;

    private Integer itemType;

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

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getTf() {
        return tf;
    }

    public void setTf(Integer tf) {
        this.tf = tf;
    }

    public BigDecimal getIdf() {
        return idf;
    }

    public void setIdf(BigDecimal idf) {
        this.idf = idf;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }
}