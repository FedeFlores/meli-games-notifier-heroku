
package com.meli.games.notifier.ws.meli.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attribute {

    private Double source;
    private String id;
    private String name;
    private String valueId;
    private String attributeGroupId;
    private String attributeGroupName;
    private String valueName;
    private ValueStruct valueStruct;
    private List<Value> values = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getSource() {
        return source;
    }

    public void setSource(Double source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }

    public String getAttributeGroupName() {
        return attributeGroupName;
    }

    public void setAttributeGroupName(String attributeGroupName) {
        this.attributeGroupName = attributeGroupName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public ValueStruct getValueStruct() {
        return valueStruct;
    }

    public void setValueStruct(ValueStruct valueStruct) {
        this.valueStruct = valueStruct;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
