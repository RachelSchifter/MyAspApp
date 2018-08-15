package com.example.owner.jniproperties;

public class SystemProperty {
    private String propertyName;
    private String property;

    /**
     * @param key   - property name as key
     * @param value - property value as value
     */
    SystemProperty(String key, String value) {
        propertyName = key;
        property = value;
    }

    /**
     * the method return the property name
     *
     * @return property name
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * the method returns the property value
     *
     * @returnthe property value
     */
    public String getProperty() {
        return property;
    }
}