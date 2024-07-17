package ymz.coffeerep.repository.selfdripcoffee;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffeeDAO;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffeeDatabase;

public class SelfDripCoffeeRepository {

    private SelfDripCoffeeDAO _selfdripcoffeeDao;
    private LiveData<SelfDripCoffee> _selfdripcoffee;
    private LiveData<List<SelfDripCoffee>> _allSelfDripCoffee;
    //private int flag = 0;

    //constructor
    public SelfDripCoffeeRepository(Application application) {
        SelfDripCoffeeDatabase db = SelfDripCoffeeDatabase.getDatabase(application);
        _selfdripcoffeeDao = db.selfdripcoffeeDao();
        _allSelfDripCoffee = _selfdripcoffeeDao.getAllSelfDripCoffee();
    }

    //get list from DAO and return it directly
    public LiveData<List<SelfDripCoffee>> getAllSelfDripCoffee() {
        return _allSelfDripCoffee;
    }

    //get selfdripcoffee from DAO and return it directly
    public LiveData<SelfDripCoffee> getSelfDripCoffee(int id){
        _selfdripcoffee = _selfdripcoffeeDao.getSelfDripCoffee(id);
        return _selfdripcoffee;
    }

    //insert (DB access is only by worker thread)
    public void insert(SelfDripCoffee newSelfDripCoffee){
        SelfDripCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _selfdripcoffeeDao.insertSelfDripCoffee(newSelfDripCoffee);
        });
    }

    //update (DB access is only by worker thread)
    public SelfDripCoffee update(SelfDripCoffee newSelfDripCoffee){
        SelfDripCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _selfdripcoffeeDao.updateSelfDripCoffee(newSelfDripCoffee);
        });
        return newSelfDripCoffee;
    }

    //delete (DB access is only by worker thread)
    public void delete(SelfDripCoffee selfdripcoffee){
        SelfDripCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _selfdripcoffeeDao.deleteSelfDripCoffee(selfdripcoffee);
        });
    }
}
