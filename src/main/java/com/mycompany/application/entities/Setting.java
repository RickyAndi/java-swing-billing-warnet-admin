package com.mycompany.application.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="settings", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Setting {
}
