package bank;

import java.util.Date;

public class TimeProvider {
    private Date currentDate;
    private Date lastAccountsUpdate;

    public TimeProvider(Date initialDate) {
        currentDate = initialDate;
        lastAccountsUpdate = initialDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getLastAccountsUpdate() {
        return lastAccountsUpdate;
    }

    public void setLastAccountsUpdate(Date lastAccountsUpdate) {
        this.lastAccountsUpdate = lastAccountsUpdate;
    }


}
