package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansDetailViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;
    //private final LiveData<RawBeans> _rawBeans;

    //constructor
    public RawBeansDetailViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
        //_rawBeans = _repository.getRawBeans();
    }

    //get list from repository and return it directly
    /*
    LiveData<RawBeans> getRawBeans() {
        return _rawBeans;
    }
    */
}
