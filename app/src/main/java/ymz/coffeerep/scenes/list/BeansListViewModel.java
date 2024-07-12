package ymz.coffeerep.scenes.list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class BeansListViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;
    private final LiveData<List<RawBeans>> _allRawBeans;

    //constructor
    public BeansListViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
        _allRawBeans = _repository.getAllRawBeans();
    }

    //get list from repository and return it directly
    LiveData<List<RawBeans>> getAllRawBeans() {
        return _allRawBeans;
    }
}
