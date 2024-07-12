package ymz.coffeerep.repository.rawbeans;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.rawbeans.RawBeansDAO;
import ymz.coffeerep.data.rawbeans.RawBeansDatabase;

public class RawBeansRepository {

    private RawBeansDAO mRawbeansDao;
    private LiveData<RawBeans> mRawbeans;
    private LiveData<List<RawBeans>> mAllRawBeans;
    //private int flag = 0;

    //constructor
    public RawBeansRepository(Application application) {
        RawBeansDatabase db = RawBeansDatabase.getDatabase(application);
        mRawbeansDao = db.rawbeansDao();
        mAllRawBeans = mRawbeansDao.getAllRawBeans();
    }

    //get list from DAO and return it directly
    public LiveData<List<RawBeans>> getAllRawBeans() {
        return mAllRawBeans;
    }

    //public LiveData<RawBeans> getRawBeans(String name, long time){
    //    mRawbeans = mRawbeansDao.getRawBeans(name, time);
    //    return mRawbeans;
    //}

    public void insert(RawBeans rawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            mRawbeansDao.insertRawBeans(rawbeans);
        });
    }

}