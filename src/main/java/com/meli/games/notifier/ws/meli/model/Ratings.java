
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class Ratings {

    private Integer negative;
    private Integer positive;
    private Integer neutral;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getNeutral() {
        return neutral;
    }

    public void setNeutral(Integer neutral) {
        this.neutral = neutral;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
