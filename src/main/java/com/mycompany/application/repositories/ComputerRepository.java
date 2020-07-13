package com.mycompany.application.repositories;

import com.mycompany.application.entities.Computer;
import com.mycompany.application.enums.ComputerStatusEnum;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ComputerRepository {
    private Session session;

    public ComputerRepository(Session session) {
        this.session = session;
    }

    public List<Computer> getComputers() {
        session.clear();
        Query<Computer> query = session
            .createQuery("from Computer");
        List<Computer> computers = query.list();
        return computers;
    }

    public Optional<Computer> getComputerByName(String name) {
        Query<Computer> query = session.createQuery("from Computer C where C.name = :name");
        query.setParameter("name", name);
        return Optional.ofNullable(query.uniqueResult());
    }

    public void updateComputerToActiveStatus(
        Computer computer,
        String currentUsername,
        Double currentPricePerHour,
        Date startDateTime
    ) {
        computer.setStatus(ComputerStatusEnum.ACTIVE.value);
        computer.setCurrentUsername(currentUsername);
        computer.setCurrentPricePerHour(currentPricePerHour);
        computer.setLastStart(startDateTime);
        session.update(computer);
    }

    public void markAsInactive(Computer computer) {
        computer.setCurrentUsername(null);
        computer.setCurrentPricePerHour(null);
        computer.setLastStart(null);
        computer.setStatus(ComputerStatusEnum.INACTIVE.value);

        session.update(computer);
    }
}
