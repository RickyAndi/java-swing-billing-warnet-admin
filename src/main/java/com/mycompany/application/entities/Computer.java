package com.mycompany.application.entities;

import com.mycompany.application.enums.ComputerStatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="computers", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_username")
    private String currentUsername;

    @Column(name = "last_start")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastStart;

    @Column(name = "status")
    private Integer status;

    @Column(name = "current_price_per_hour")
    private Double currentPricePerHour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setLastStart(Date lastStart) {
        this.lastStart = lastStart;
    }

    public Date getLastStart() {
        return lastStart;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Boolean isActive() {
        return this.getStatus().equals(ComputerStatusEnum.ACTIVE.value);
    }

    public void setCurrentPricePerHour(Double currentPricePerHour) {
        this.currentPricePerHour = currentPricePerHour;
    }

    public Double getCurrentPricePerHour() {
        return currentPricePerHour;
    }
}
