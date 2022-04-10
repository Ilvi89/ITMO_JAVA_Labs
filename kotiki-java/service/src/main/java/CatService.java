import com.sun.istack.NotNull;
import dao.CatDao;
import model.Cat;
import model.CatColor;

import java.util.List;

public class CatService {
    private static CatDao catDao;

    public CatService(CatDao catDao) {
        CatService.catDao = catDao;
    }

    public Cat create(@NotNull Cat cat) {
        catDao.openSessionWithTransaction();
        catDao.save(cat);
        catDao.closeSessionWithTransaction();
        return cat;
    }

    public Cat update(@NotNull Cat cat) {
        catDao.openSessionWithTransaction();
        catDao.update(cat);
        catDao.closeSessionWithTransaction();
        return cat;
    }

    public Cat findById(@NotNull Long id) {
        catDao.openSession();
        Cat cat = catDao.findById(id);
        catDao.closeSession();
        return cat;
    }

    public List<Cat> findAll() {
        catDao.openSession();
        List<Cat> cats = catDao.findAll();
        catDao.closeSession();
        return cats;
    }

    public Cat addFriend(@NotNull Long catId, @NotNull Cat friend) {
        catDao.openSessionWithTransaction();
        Cat cat = catDao.findById(catId);
        cat.getFriends().add(friend);
        catDao.closeSessionWithTransaction();
        return cat;
    }
}
