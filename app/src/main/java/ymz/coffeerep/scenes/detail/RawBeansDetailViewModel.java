package ymz.coffeerep.scenes.detail;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Iterator;
import java.util.List;

import ymz.coffeerep.data.dropdown.DropDownDefault;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RawBeansDetailViewModel extends AndroidViewModel {

    private RawBeansRepository _rawRep;
    private RoastBeansRepository _roastRep;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> completeUpdate = new MutableLiveData<>(false);
    protected MutableLiveData<Boolean> completeDelete = new MutableLiveData<>(false);

    //constructor
    public RawBeansDetailViewModel(Application application) {
        super(application);
        _rawRep = new RawBeansRepository(application);
        _roastRep = new RoastBeansRepository(application);
    }

    //get roastbeans (need-to-update) list from repository and return it directly
    LiveData<List<RoastBeans>> getRoastbeansNeedUpdate(int rawbeans_id) {
        return _roastRep.getRoastBeansByRoastRawbeansId(rawbeans_id);
    }

    //update roastbeans (need-to-update)
    void updateRoastbeansRoastRawbeansId(List<RoastBeans> roastbeansNeedUpdate){
        if(roastbeansNeedUpdate != null){
            Iterator<RoastBeans> iterator = roastbeansNeedUpdate.iterator();
            while (iterator.hasNext()) {
                RoastBeans item = iterator.next();
                item.setRoastbeans_roast_rawbeans_id(DropDownDefault.getDefault_id());
                _roastRep.update(item);
            }
        }
        completeUpdate.setValue(true);
    }

    void delete(RawBeans rawbeans) {
        try{
            _rawRep.delete(rawbeans);
            completeDelete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
