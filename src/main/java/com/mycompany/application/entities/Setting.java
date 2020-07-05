package com.mycompany.application.entities;

import javax.persistence.*;

@Entity
@Table(name="settings", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "string_value")
    private String stringValue;

    @Column(name = "integer_value")
    private Integer integerValue;

    @Column(name = "decimal_value")
    private Double decimalValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Double getDecimalValue() {
        return decimalValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }
}
