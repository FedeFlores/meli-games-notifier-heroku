
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class DelayedHandlingTime {

    private Double rate;
    private Excluded_ excluded;
    private Integer value;
    private String period;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Excluded_ getExcluded() {
        return excluded;
    }

    public void setExcluded(Excluded_ excluded) {
        this.excluded = excluded;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
