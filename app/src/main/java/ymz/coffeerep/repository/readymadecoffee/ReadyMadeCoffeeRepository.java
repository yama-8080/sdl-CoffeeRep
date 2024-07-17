package ymz.coffeerep.repository.readymadecoffee;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeDAO;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeDatabase;

public class ReadyMadeCoffeeRepository {

    private ReadyMadeCoffeeDAO _readymadecoffeeDao;
    private LiveData<ReadyMadeCoffee> _readymadecoffee;
    private LiveData<List<ReadyMadeCoffee>> _allReadyMadeCoffee;
    //private int flag = 0;

    //constructor
    public ReadyMadeCoffeeRepository(Application application) {
        ReadyMadeCoffeeDatabase db = ReadyMadeCoffeeDatabase.getDatabase(application);
        _readymadecoffeeDao = db.readymadecoffeeDao();
        _allReadyMadeCoffee = _readymadecoffeeDao.getAllReadyMadeCoffee();
    }

    //get list from DAO and return it directly
    public LiveData<List<ReadyMadeCoffee>> getAllReadyMadeCoffee() {
        return _allReadyMadeCoffee;
    }

    //get readymadecoffee from DAO and return it directly
    public LiveData<ReadyMadeCoffee> getReadyMadeCoffee(int id){
        _readymadecoffee = _readymadecoffeeDao.getReadyMadeCoffee(id);
        return _readymadecoffee;
    }

    //insert (DB access is only by worker thread)
    public void insert(ReadyMadeCoffee newReadyMadeCoffee){
        ReadyMadeCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _readymadecoffeeDao.insertReadyMadeCoffee(newReadyMadeCoffee);
        });
    }

    //update (DB access is only by worker thread)
    public ReadyMadeCoffee update(ReadyMadeCoffee newReadyMadeCoffee){
        ReadyMadeCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _readymadecoffeeDao.updateReadyMadeCoffee(newReadyMadeCoffee);
        });
        return newReadyMadeCoffee;
    }

    //delete (DB access is only by worker thread)
    public void delete(ReadyMadeCoffee readymadecoffee){
        ReadyMadeCoffeeDatabase.databaseWriteExecutor.execute(() -> {
            _readymadecoffeeDao.deleteReadyMadeCoffee(readymadecoffee);
        });
    }
}
