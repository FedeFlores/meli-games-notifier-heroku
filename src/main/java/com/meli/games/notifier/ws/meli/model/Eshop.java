
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eshop {

    private String nickName;
    private Object eshopRubro;
    private Integer eshopId;
    private List<Object> eshopLocations = null;
    private String siteId;
    private String eshopLogoUrl;
    private Integer eshopStatusId;
    private Integer seller;
    private Integer eshopExperience;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Object getEshopRubro() {
        return eshopRubro;
    }

    public void setEshopRubro(Object eshopRubro) {
        this.eshopRubro = eshopRubro;
    }

    public Integer getEshopId() {
        return eshopId;
    }

    public void setEshopId(Integer eshopId) {
        this.eshopId = eshopId;
    }

    public List<Object> getEshopLocations() {
        return eshopLocations;
    }

    public void setEshopLocations(List<Object> eshopLocations) {
        this.eshopLocations = eshopLocations;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getEshopLogoUrl() {
        return eshopLogoUrl;
    }

    public void setEshopLogoUrl(String eshopLogoUrl) {
        this.eshopLogoUrl = eshopLogoUrl;
    }

    public Integer getEshopStatusId() {
        return eshopStatusId;
    }

    public void setEshopStatusId(Integer eshopStatusId) {
        this.eshopStatusId = eshopStatusId;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public Integer getEshopExperience() {
        return eshopExperience;
    }

    public void setEshopExperience(Integer eshopExperience) {
        this.eshopExperience = eshopExperience;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
