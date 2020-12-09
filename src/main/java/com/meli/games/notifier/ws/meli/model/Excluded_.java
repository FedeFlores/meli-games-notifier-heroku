
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class Excluded_ {

    private Double realRate;
    private Integer realValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getRealRate() {
        return realRate;
    }

    public void setRealRate(Double realRate) {
        this.realRate = realRate;
    }

    public Integer getRealValue() {
        return realValue;
    }

    public void setRealValue(Integer realValue) {
        this.realValue = realValue;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
