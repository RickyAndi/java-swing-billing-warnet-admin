package com.mycompany.application.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transactions", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @Column(name = "username")
    private String username;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_on")
    private Date startOn;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ends_on")
    private Date endsOn;

    @Column(name = "status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setStartOn(Date startOn) {
        this.startOn = startOn;
    }

    public Date getStartOn() {
        return startOn;
    }

    public void setEndsOn(Date endsOn) {
        this.endsOn = endsOn;
    }

    public Date getEndsOn() {
        return endsOn;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
