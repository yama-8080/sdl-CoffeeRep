package ymz.coffeerep.scenes.list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class BeansListViewModel extends AndroidViewModel {

    private RawBeansRepository _rawRep;
    private RoastBeansRepository _RoastRep;
    private final LiveData<List<RawBeans>> _allRawBeans;
    private final LiveData<List<RoastBeans>> _allRoastBeans;

    //constructor
    public BeansListViewModel(Application application) {
        super(application);
        _rawRep = new RawBeansRepository(application);
        _RoastRep = new RoastBeansRepository(application);
        _allRawBeans = _rawRep.getAllRawBeans();
        _allRoastBeans = _RoastRep.getAllRoastBeans();
    }

    //get rawbeans list from repository and return it directly
    LiveData<List<RawBeans>> getAllRawBeans() {
        return _allRawBeans;
    }

    //get roastbeans list from repository and return it directly
    LiveData<List<RoastBeans>> getAllRoastBeans() {
        return _allRoastBeans;
    }
}
