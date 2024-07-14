package ymz.coffeerep.repository.rawbeans;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.rawbeans.RawBeansDAO;
import ymz.coffeerep.data.rawbeans.RawBeansDatabase;

public class RawBeansRepository {

    private RawBeansDAO _rawbeansDao;
    private LiveData<RawBeans> _rawbeans;
    private LiveData<List<RawBeans>> _allRawBeans;
    //private int flag = 0;

    //constructor
    public RawBeansRepository(Application application) {
        RawBeansDatabase db = RawBeansDatabase.getDatabase(application);
        _rawbeansDao = db.rawbeansDao();
        _allRawBeans = _rawbeansDao.getAllRawBeans();
    }

    //get list from DAO and return it directly
    public LiveData<List<RawBeans>> getAllRawBeans() {
        return _allRawBeans;
    }

    //get rawbeans from DAO and return it directly
    public LiveData<RawBeans> getRawBeans(int id){
        _rawbeans = _rawbeansDao.getRawBeans(id);
        return _rawbeans;
    }

    //insert (DB access is only by worker thread)
    public void insert(RawBeans rawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            _rawbeansDao.insertRawBeans(rawbeans);
        });
    }

    public RawBeans update(RawBeans newRawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            _rawbeansDao.updateRawBeans(newRawbeans);
        });
        return newRawbeans;
    }
}
