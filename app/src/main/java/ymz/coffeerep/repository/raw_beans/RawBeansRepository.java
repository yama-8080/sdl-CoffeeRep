package ymz.coffeerep.repository.raw_beans;

import android.app.Application;

import androidx.lifecycle.LiveData;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.data.raw_beans.RawBeansDAO;
import ymz.coffeerep.data.raw_beans.RawBeansDatabase;

public class RawBeansRepository {

    private RawBeansDAO mRawbeansDao;
    private LiveData<RawBeans> mRawbeans;
    private int flag = 0;

    public RawBeansRepository(Application application) {
        RawBeansDatabase db = RawBeansDatabase.getDatabase(application);
        mRawbeansDao = db.rawbeansDao();
    }

    public LiveData<RawBeans> getRawBeans(String name, long time){
        mRawbeans = mRawbeansDao.getRawBeans(name, time);
        return mRawbeans;
    }

    public void insert(RawBeans rawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            mRawbeansDao.insertRawBeans(rawbeans);
        });
    }

}
