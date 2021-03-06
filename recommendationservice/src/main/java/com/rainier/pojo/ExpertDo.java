package com.rainier.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name="expert")
public class ExpertDo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.id
     *
     * @mbggenerated
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.uuid
     *
     * @mbggenerated
     */
    @GeneratedValue(generator="UUID")
    private String uuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.head_img_url
     *
     * @mbggenerated
     */
    private String headImgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.domain
     *
     * @mbggenerated
     */
    private String domain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.level
     *
     * @mbggenerated
     */
    private String level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.organization
     *
     * @mbggenerated
     */
    private String organization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.created
     *
     * @mbggenerated
     */
    private Timestamp created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.modified
     *
     * @mbggenerated
     */
    private Timestamp modified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.modified_by
     *
     * @mbggenerated
     */
    private String modifiedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column expert.create_user_name
     *
     * @mbggenerated
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table expert
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.id
     *
     * @return the value of expert.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.id
     *
     * @param id the value for expert.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.uuid
     *
     * @return the value of expert.uuid
     *
     * @mbggenerated
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.uuid
     *
     * @param uuid the value for expert.uuid
     *
     * @mbggenerated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.name
     *
     * @return the value of expert.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.name
     *
     * @param name the value for expert.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.head_img_url
     *
     * @return the value of expert.head_img_url
     *
     * @mbggenerated
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.head_img_url
     *
     * @param headImgUrl the value for expert.head_img_url
     *
     * @mbggenerated
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.introduction
     *
     * @return the value of expert.introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.introduction
     *
     * @param introduction the value for expert.introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.domain
     *
     * @return the value of expert.domain
     *
     * @mbggenerated
     */
    public String getDomain() {
        return domain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.domain
     *
     * @param domain the value for expert.domain
     *
     * @mbggenerated
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.level
     *
     * @return the value of expert.level
     *
     * @mbggenerated
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.level
     *
     * @param level the value for expert.level
     *
     * @mbggenerated
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.organization
     *
     * @return the value of expert.organization
     *
     * @mbggenerated
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.organization
     *
     * @param organization the value for expert.organization
     *
     * @mbggenerated
     */
    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.created
     *
     * @return the value of expert.created
     *
     * @mbggenerated
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.created
     *
     * @param created the value for expert.created
     *
     * @mbggenerated
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.modified
     *
     * @return the value of expert.modified
     *
     * @mbggenerated
     */
    public Timestamp getModified() {
        return modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.modified
     *
     * @param modified the value for expert.modified
     *
     * @mbggenerated
     */
    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.modified_by
     *
     * @return the value of expert.modified_by
     *
     * @mbggenerated
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.modified_by
     *
     * @param modifiedBy the value for expert.modified_by
     *
     * @mbggenerated
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.create_by
     *
     * @return the value of expert.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.create_by
     *
     * @param createBy the value for expert.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column expert.create_user_name
     *
     * @return the value of expert.create_user_name
     *
     * @mbggenerated
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column expert.create_user_name
     *
     * @param createUserName the value for expert.create_user_name
     *
     * @mbggenerated
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }
}