package ymz.coffeerep.repository.raw_beans;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.data.raw_beans.RawBeansDAO;
import ymz.coffeerep.data.raw_beans.RawBeansDatabaseOld;

public class RawBeansRepositoryOld {

    private RawBeansDAO mRawbeansDao;
    private LiveData<RawBeans> mRawbeans;
    private int flag = 0;

    public RawBeansRepositoryOld(Application application) {
        RawBeansDatabaseOld db = RawBeansDatabaseOld.getDatabase(application);
        mRawbeansDao = db.rawbeansDao();
    }

    public LiveData<RawBeans> getRawBeans(String name, long time){
        mRawbeans = mRawbeansDao.getRawBeans(name, time);
        return mRawbeans;
    }

    public void insert(RawBeans rawbeans){
        RawBeansDatabaseOld.databaseWriteExecutor.execute(() -> {
            mRawbeansDao.insertRawBeans(rawbeans);
        });
    }

}
