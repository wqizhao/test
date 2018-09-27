package com.rainier.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: NewsModel
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2416:44
 */
public class ResourceModel {

//    private Long id;

    private Long totalNum;

    private String uuid;

//    private String type;

    private String title;

    private String imgUrl;
    private  String userName;
    private String topName;
    private  String userUuid;
    private Long likeNum;
    private Long commentNum;
    private Long shareNum;
    private Long browseNum;
    private Long downloadNum;
    private Long favoriteNum;
    private Long noteNum;
    private Long dislikeNum;
    private Date createDate;
    private String createDateStr;
    private BigDecimal duration;
    private String durationStr;
    private String keyWord;
    private String fileUrl;
    private String type;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    private String[] keyWords;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Long getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(Long dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Long getShareNum() {
        return shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public Long getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Long browseNum) {
        this.browseNum = browseNum;
    }

    public Long getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Long downloadNum) {
        this.downloadNum = downloadNum;
    }

    public Long getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(Long favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public Long getNoteNum() {
        return noteNum;
    }

    public void setNoteNum(Long noteNum) {
        this.noteNum = noteNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//    private String source;
//
//    private String author;
//
//    private String url;
//
//    private String issueDate;
//
//    private String county;
//
//    private String province;
//
//    private String city;
//
//    private Date sourceDate;
//
//    private Date created;
//
//    private Date modified;
//
//    private String createBy;
//
//    private String modifiedBy;
//
//    private String state;
//
//    private String sourceId;
//
//    private String sourceCounty;
//
//    private String sourceProvince;
//
//    private String sourceCity;
//
//    private Boolean headline;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

////    public String getSource() {
////        return source;
////    }
////
////    public void setSource(String source) {
////        this.source = source;
////    }
////
////    public String getAuthor() {
////        return author;
////    }
////
////    public void setAuthor(String author) {
////        this.author = author;
////    }
////
////    public String getUrl() {
////        return url;
////    }
////
////    public void setUrl(String url) {
////        this.url = url;
////    }
//
//    public String getIssueDate() {
//        return issueDate;
//    }
//
//    public void setIssueDate(String issueDate) {
//        this.issueDate = issueDate;
//    }
//
//    public String getCounty() {
//        return county;
//    }
//
//    public void setCounty(String county) {
//        this.county = county;
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public Date getSourceDate() {
//        return sourceDate;
//    }
//
//    public void setSourceDate(Date sourceDate) {
//        this.sourceDate = sourceDate;
//    }
//
//    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//
//    public Date getModified() {
//        return modified;
//    }
//
//    public void setModified(Date modified) {
//        this.modified = modified;
//    }
//
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getSourceId() {
//        return sourceId;
//    }
//
//    public void setSourceId(String sourceId) {
//        this.sourceId = sourceId;
//    }
//
//    public String getSourceCounty() {
//        return sourceCounty;
//    }
//
//    public void setSourceCounty(String sourceCounty) {
//        this.sourceCounty = sourceCounty;
//    }
//
//    public String getSourceProvince() {
//        return sourceProvince;
//    }
//
//    public void setSourceProvince(String sourceProvince) {
//        this.sourceProvince = sourceProvince;
//    }
//
//    public String getSourceCity() {
//        return sourceCity;
//    }
//
//    public void setSourceCity(String sourceCity) {
//        this.sourceCity = sourceCity;
//    }
//
//    public Boolean getHeadline() {
//        return headline;
//    }
//
//    public void setHeadline(Boolean headline) {
//        this.headline = headline;
//    }
}
