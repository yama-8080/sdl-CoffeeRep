package ymz.coffeerep.scenes.list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.repository.readymadecoffee.ReadyMadeCoffeeRepository;
import ymz.coffeerep.repository.selfdripcoffee.SelfDripCoffeeRepository;

public class CoffeeListViewModel extends AndroidViewModel {

    private ReadyMadeCoffeeRepository _rawRep;
    private SelfDripCoffeeRepository _roastRep;
    private final LiveData<List<ReadyMadeCoffee>> _allReadyMadeCoffee;
    private final LiveData<List<SelfDripCoffee>> _allSelfDripCoffee;

    //constructor
    public CoffeeListViewModel(Application application) {
        super(application);
        _rawRep = new ReadyMadeCoffeeRepository(application);
        _roastRep = new SelfDripCoffeeRepository(application);
        _allReadyMadeCoffee = _rawRep.getAllReadyMadeCoffee();
        _allSelfDripCoffee = _roastRep.getAllSelfDripCoffee();
    }

    //get readymadecoffee list from repository and return it directly
    LiveData<List<ReadyMadeCoffee>> getAllReadyMadeCoffee() {
        return _allReadyMadeCoffee;
    }

    //get selfdripcoffee list from repository and return it directly
    LiveData<List<SelfDripCoffee>> getAllSelfDripCoffee() {
        return _allSelfDripCoffee;
    }
}
