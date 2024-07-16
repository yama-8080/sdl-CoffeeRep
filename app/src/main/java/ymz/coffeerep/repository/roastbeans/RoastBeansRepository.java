package ymz.coffeerep.repository.roastbeans;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.roastbeans.RoastBeansDAO;
import ymz.coffeerep.data.roastbeans.RoastBeansDatabase;

public class RoastBeansRepository {

    private RoastBeansDAO _roastbeansDao;
    private LiveData<RoastBeans> _roastbeans;
    private LiveData<List<RoastBeans>> _allRoastBeans;
    //private int flag = 0;

    //constructor
    public RoastBeansRepository(Application application) {
        RoastBeansDatabase db = RoastBeansDatabase.getDatabase(application);
        _roastbeansDao = db.roastbeansDao();
        _allRoastBeans = _roastbeansDao.getAllRoastBeans();
    }

    //get list from DAO and return it directly
    public LiveData<List<RoastBeans>> getAllRoastBeans() {
        return _allRoastBeans;
    }

    //get roastbeans from DAO and return it directly
    public LiveData<RoastBeans> getRoastBeans(int id){
        _roastbeans = _roastbeansDao.getRoastBeans(id);
        return _roastbeans;
    }

    //insert (DB access is only by worker thread)
    public void insert(RoastBeans newRoastbeans){
        RoastBeansDatabase.databaseWriteExecutor.execute(() -> {
            _roastbeansDao.insertRoastBeans(newRoastbeans);
        });
    }

    //update (DB access is only by worker thread)
    public RoastBeans update(RoastBeans newRoastbeans){
        RoastBeansDatabase.databaseWriteExecutor.execute(() -> {
            _roastbeansDao.updateRoastBeans(newRoastbeans);
        });
        return newRoastbeans;
    }

    //delete (DB access is only by worker thread)
    public void delete(RoastBeans roastbeans){
        RoastBeansDatabase.databaseWriteExecutor.execute(() -> {
            _roastbeansDao.deleteRoastBeans(roastbeans);
        });
    }
}
