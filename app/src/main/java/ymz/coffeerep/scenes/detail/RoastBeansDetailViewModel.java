package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Iterator;
import java.util.List;

import ymz.coffeerep.data.dropdown.DropDownDefault;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;
import ymz.coffeerep.repository.selfdripcoffee.SelfDripCoffeeRepository;

public class RoastBeansDetailViewModel extends AndroidViewModel {

    private RoastBeansRepository _roastRep;
    private RawBeansRepository _rawRep;
    private SelfDripCoffeeRepository _selfRep;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> completeUpdate = new MutableLiveData<>(false);
    protected MutableLiveData<Boolean> completeDelete = new MutableLiveData<>(false);

    //constructor
    public RoastBeansDetailViewModel(Application application) {
        super(application);
        _roastRep = new RoastBeansRepository(application);
        _rawRep = new RawBeansRepository(application);
        _selfRep = new SelfDripCoffeeRepository(application);
    }

    LiveData<RawBeans> getRawBeans(int id){
        return _rawRep.getRawBeans(id);
    }

    //get selfdripcoffee (need-to-update) list from repository and return it directly
    LiveData<List<SelfDripCoffee>> getSelfdripcoffeeNeedUpdate(int roastbeans_id) {
        return _selfRep.getSelfDripCoffeeByRoastbeansId(roastbeans_id);
    }

    //update selfdripcoffee (need-to-update)
    void updateSelfdripcoffeeRoastbeansId(List<SelfDripCoffee> selfdripcoffeeNeedUpdate){
        if(selfdripcoffeeNeedUpdate != null){
            Iterator<SelfDripCoffee> iterator = selfdripcoffeeNeedUpdate.iterator();
            while (iterator.hasNext()) {
                SelfDripCoffee item = iterator.next();
                item.setSelfdripcoffee_roastbeans(DropDownDefault.getDefault_id());
                _selfRep.update(item);
            }
        }
        completeUpdate.setValue(true);
    }

    void delete(RoastBeans roastbeans) {
        try{
            _roastRep.delete(roastbeans);
            completeDelete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
