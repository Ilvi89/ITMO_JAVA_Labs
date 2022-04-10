package dao;

import model.Cat;
import model.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OwnerDao extends BaseDao<Owner, Long>{


    @Override
    public Owner findById(Long id) {
        return getSession().get(Owner.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Owner> findAll() {
        return (List<Owner>) getSession().createQuery("from Owner").list();
    }
}
