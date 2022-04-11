package ilvi89.dao;

import ilvi89.model.Owner;

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
