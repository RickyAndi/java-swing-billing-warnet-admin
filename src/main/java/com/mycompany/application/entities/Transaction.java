package com.mycompany.application.entities;

import com.mycompany.application.enums.TransactionStatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transactions", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
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
    @Column(name = "end_on")
    private Date endOn;

    @Column(name = "amount_to_be_paid")
    private Double amountToBePaid;

    @Column(name = "amount_paid_by_client")
    private Double amountPaidByClient;

    @Column(name = "amount_change")
    private Double amountChange;

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

    public void setEndOn(Date endOn) {
        this.endOn = endOn;
    }

    public Date getEndOn() {
        return endOn;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void setAmountToBePaid(Double amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public Double getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountChange(Double amountChange) {
        this.amountChange = amountChange;
    }

    public Double getAmountChange() {
        return amountChange;
    }

    public void setAmountPaidByClient(Double amountPaidByClient) {
        this.amountPaidByClient = amountPaidByClient;
    }

    public Double getAmountPaidByClient() {
        return amountPaidByClient;
    }

    public Boolean isActive() {
        return this.getStatus().equals(TransactionStatusEnum.ACTIVE.value);
    }

    public Boolean isNotPaid() {
        return this.getStatus().equals(TransactionStatusEnum.NOT_PAID.value);
    }

    public Boolean isPaid() {
        return this.getStatus().equals(TransactionStatusEnum.PAID.value);
    }

    public Double getTotalTariffFromDuration(Double costPerHour) {
        if (this.isActive()) {
            return 0.0;
        }

        return 0.0;
    }
}
