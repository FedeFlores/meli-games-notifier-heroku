
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class SellerReputation {

    private Transactions transactions;
    private Object powerSellerStatus;
    private Metrics metrics;
    private String protectionEndDate;
    private String realLevel;
    private String levelId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public Object getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(Object powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public String getProtectionEndDate() {
        return protectionEndDate;
    }

    public void setProtectionEndDate(String protectionEndDate) {
        this.protectionEndDate = protectionEndDate;
    }

    public String getRealLevel() {
        return realLevel;
    }

    public void setRealLevel(String realLevel) {
        this.realLevel = realLevel;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
