package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansDetailViewModel extends AndroidViewModel {

    private RoastBeansRepository _roastRep;
    private RawBeansRepository _rawRep;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RoastBeansDetailViewModel(Application application) {
        super(application);
        _roastRep = new RoastBeansRepository(application);
        _rawRep = new RawBeansRepository(application);
    }

    LiveData<RawBeans> getRawBeans(int id){
        return _rawRep.getRawBeans(id);
    }

    void delete(RoastBeans roastbeans) {
        try{
            _roastRep.delete(roastbeans);
            complete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
