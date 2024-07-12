package ymz.coffeerep.scenes.list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class BeansListViewModel extends AndroidViewModel {

    private RawBeansRepository mRepository;
    private final LiveData<List<RawBeans>> mAllRawBeans;

    //constructor
    public BeansListViewModel(Application application) {
        super(application);
        mRepository = new RawBeansRepository(application);
        mAllRawBeans = mRepository.getAllRawBeans();
    }

    //get list from repository and return it directly
    LiveData<List<RawBeans>> getAllRawBeans() {
        return mAllRawBeans;
    }
}
