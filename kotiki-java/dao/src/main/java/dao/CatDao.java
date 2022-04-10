package dao;

import model.Cat;
import model.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CatDao extends BaseDao<Cat, Long> {


    @Override
    public Cat findById(Long id) {
        return getSession().get(Cat.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cat> findAll() {
        return (List<Cat>) getSession().createQuery("from Cat").list();
    }
}
