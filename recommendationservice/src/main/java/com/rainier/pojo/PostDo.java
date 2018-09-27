package com.rainier.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="post")
public class PostDo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @GeneratedValue(generator="UUID")
    private String uuid;

    private String title;

    private Boolean essencePost;

    private Boolean adminTop;

    private Boolean userTop;

    private Boolean enableComment;

    private Boolean question;

    private String state;

    private Date created;

    private Date modified;

    private String createBy;

    private String modifiedBy;

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
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getEssencePost() {
        return essencePost;
    }

    public void setEssencePost(Boolean essencePost) {
        this.essencePost = essencePost;
    }

    public Boolean getAdminTop() {
        return adminTop;
    }

    public void setAdminTop(Boolean adminTop) {
        this.adminTop = adminTop;
    }

    public Boolean getUserTop() {
        return userTop;
    }

    public void setUserTop(Boolean userTop) {
        this.userTop = userTop;
    }

    public Boolean getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Boolean enableComment) {
        this.enableComment = enableComment;
    }

    public Boolean getQuestion() {
        return question;
    }

    public void setQuestion(Boolean question) {
        this.question = question;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}