package com.mycompany.application.repositories;

import com.mycompany.application.entities.Computer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ComputerRepository {
    private Session session;

    public ComputerRepository(Session session) {
        this.session = session;
    }

    public List<Computer> getComputers() {
        Query query = session.createQuery("from Computer");
        List<Computer> computers = query.getResultList();
        return computers;
    }
}
