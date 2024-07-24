package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;
import ymz.coffeerep.repository.selfdripcoffee.SelfDripCoffeeRepository;

public class SelfDripCoffeeDetailViewModel extends AndroidViewModel {

    private SelfDripCoffeeRepository _selfRep;
    private RoastBeansRepository roastRep;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public SelfDripCoffeeDetailViewModel(Application application) {
        super(application);
        _selfRep = new SelfDripCoffeeRepository(application);
        roastRep = new RoastBeansRepository(application);
    }

    LiveData<RoastBeans> getRoastBeans(int id){
        return roastRep.getRoastBeans(id);
    }

    void delete(SelfDripCoffee selfdripcoffee) {
        try{
            _selfRep.delete(selfdripcoffee);
            complete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
