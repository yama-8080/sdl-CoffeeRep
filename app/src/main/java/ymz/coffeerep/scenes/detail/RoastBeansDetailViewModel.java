package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansDetailViewModel extends AndroidViewModel {

    private RoastBeansRepository _repository;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RoastBeansDetailViewModel(Application application) {
        super(application);
        _repository = new RoastBeansRepository(application);
    }

    void delete(RoastBeans roastbeans) {
        try{
            _repository.delete(roastbeans);
            complete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
