
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.Map;

public class Sales {

    private String period;
    private Integer completed;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
