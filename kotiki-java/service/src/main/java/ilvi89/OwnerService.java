package ilvi89;

import com.sun.istack.NotNull;
import ilvi89.dao.OwnerDao;
import ilvi89.model.Cat;
import ilvi89.model.Owner;

import java.util.List;

public class OwnerService{
    private static OwnerDao ownerDao;

    public OwnerService(OwnerDao ownerDao) {
        OwnerService.ownerDao = ownerDao;
    }

    public Owner create(@NotNull Owner owner) {
        ownerDao.openSessionWithTransaction();
        ownerDao.save(owner);
        ownerDao.closeSessionWithTransaction();
        return owner;
    }

    public Owner update(@NotNull Owner owner) {
        ownerDao.openSessionWithTransaction();
        ownerDao.update(owner);
        ownerDao.closeSessionWithTransaction();
        return owner;
    }

    public Owner findById(@NotNull Long id) {
        ownerDao.openSession();
        Owner owner = ownerDao.findById(id);
        ownerDao.closeSession();
        return owner;
    }

    public List<Owner> findAll() {
        ownerDao.openSession();
        List<Owner> owners = ownerDao.findAll();
        ownerDao.closeSession();
        return owners;
    }

    public Owner addFriend(@NotNull Long id, @NotNull Cat cat) {
        ownerDao.openSessionWithTransaction();
        Owner owner = ownerDao.findById(id);
        owner.getCats().add(cat);
        ownerDao.closeSessionWithTransaction();
        return owner;
    }
}
