
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class Metrics {

    private Claims claims;
    private DelayedHandlingTime delayedHandlingTime;
    private Sales sales;
    private Cancellations cancellations;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public DelayedHandlingTime getDelayedHandlingTime() {
        return delayedHandlingTime;
    }

    public void setDelayedHandlingTime(DelayedHandlingTime delayedHandlingTime) {
        this.delayedHandlingTime = delayedHandlingTime;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Cancellations getCancellations() {
        return cancellations;
    }

    public void setCancellations(Cancellations cancellations) {
        this.cancellations = cancellations;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
