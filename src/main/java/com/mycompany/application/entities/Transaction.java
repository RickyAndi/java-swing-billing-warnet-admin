package com.mycompany.application.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="transactions", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Transaction {

    private int id;

    private int computer_id;
}
