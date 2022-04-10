import dao.OwnerDao;

public class OwnerTest {
    public static void main(String[] args) {
        OwnerDao ownerDao = new OwnerDao();
        ownerDao.openSession();
        ownerDao.findAll();
        ownerDao.closeSession();
    }
}
