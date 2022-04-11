package ilvi89.dao;

import ilvi89.model.Cat;

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
