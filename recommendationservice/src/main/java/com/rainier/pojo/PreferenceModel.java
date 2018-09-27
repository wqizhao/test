package com.rainier.pojo;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: FavoriteModel
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2010:18
 */
public class PreferenceModel {
    //资源uuid
    private String itemUuid;
    //收藏者uuid
    private String createBy;
    //资源收藏数
    private Integer reCommendNum;

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getReCommendNum() {
        return reCommendNum;
    }

    public void setReCommendNum(Integer reCommendNum) {
        this.reCommendNum = reCommendNum;
    }
}
