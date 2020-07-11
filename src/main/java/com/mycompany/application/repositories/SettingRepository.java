package com.mycompany.application.repositories;

import com.mycompany.application.entities.Setting;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class SettingRepository {
    private Session session;

    public SettingRepository(Session session) {
        this.session = session;
    }

    public Optional<Setting> getSettingByName(String name) {
        Query query = session.createQuery("from Setting S where S.name = :name");
        query.setParameter("name", name);

        return Optional.ofNullable((Setting) query.uniqueResult());
    }

    public Setting updateIntegerValue(Setting setting, Integer integerValue) {

        setting.setIntegerValue(integerValue);
        setting.setDecimalValue(null);
        setting.setStringValue(null);

        session.update(setting);

        return setting;
    }

    public Setting updateStringValue(Setting setting, String stringValue) {

        setting.setIntegerValue(null);
        setting.setDecimalValue(null);
        setting.setStringValue(stringValue);

        session.update(setting);

        return setting;
    }

    public Setting updateDecimalValue(Setting setting, Double decimalValue) {

        setting.setIntegerValue(null);
        setting.setDecimalValue(decimalValue);
        setting.setStringValue(null);

        session.update(setting);

        return setting;
    }
}
