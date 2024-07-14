package ymz.coffeerep.scenes.detail;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansDetailViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RawBeansDetailViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }

    void delete(RawBeans rawbeans) {
        try{
            _repository.delete(rawbeans);
            complete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
