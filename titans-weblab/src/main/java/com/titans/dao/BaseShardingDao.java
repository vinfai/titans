package com.titans.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.io.Serializable;

/**
 * @author vinfai
 * @since 2017/7/27
 */
public class BaseShardingDao {

    @Autowired@Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public <T> void save(T o) {
        hibernateTemplate.save(o);
    }

    public <T> T getObjectById(Class<T> clazz, Serializable id) {
        if (id == null || clazz == null) {
            return null;
        }
        T t = hibernateTemplate.get(clazz, id);
        return t;
    }

    public <T> void deleteObjectById(Class<T> clazz, Serializable id) {
        if (id == null || clazz == null) {
            return ;
        }
        T t = getObjectById(clazz, id);
        if (t != null) {
            hibernateTemplate.delete(t);
        }
    }

}
