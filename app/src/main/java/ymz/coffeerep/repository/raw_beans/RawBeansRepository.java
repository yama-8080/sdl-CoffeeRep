package ymz.coffeerep.repository.raw_beans;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.data.raw_beans.RawBeansDAO;
import ymz.coffeerep.data.raw_beans.RawBeansDatabase;

public class RawBeansRepository {

    private RawBeansDAO mRawbeansDao;
    private LiveData<RawBeans> mRawbeans;
    private LiveData<List<RawBeans>> mAllRawBeans;
    //private int flag = 0;

    //constructor
    public RawBeansRepository(Application application) {
        RawBeansDatabase db = RawBeansDatabase.getDatabase(application);
        mRawbeansDao = db.rawbeansDao();
        mAllRawBeans = mRawbeansDao.getAlphabetizedRawBeans();
    }

    //public LiveData<RawBeans> getRawBeans(String name, long time){
    //    mRawbeans = mRawbeansDao.getRawBeans(name, time);
    //    return mRawbeans;
    //}

    public LiveData<List<RawBeans>> getAllRawBeans() {
        return mAllRawBeans;
    }

    public void insert(RawBeans rawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            mRawbeansDao.insertRawBeans(rawbeans);
        });
    }

}
