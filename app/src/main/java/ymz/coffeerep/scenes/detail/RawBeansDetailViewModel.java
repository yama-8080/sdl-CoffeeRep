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
    //private final LiveData<RawBeans> _rawbeans;

    //constructor
    public RawBeansDetailViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }
}
