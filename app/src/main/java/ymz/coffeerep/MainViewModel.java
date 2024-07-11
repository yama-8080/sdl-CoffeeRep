package ymz.coffeerep;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.repository.raw_beans.RawBeansRepository;

public class MainViewModel extends AndroidViewModel {
    private RawBeansRepository mRepository;
    private final LiveData<List<RawBeans>> mAllRawBeans;

    //constructor
    public MainViewModel(Application application) {
        super(application);
        mRepository = new RawBeansRepository(application);
        mAllRawBeans = mRepository.getAllRawBeans();
    }

    LiveData<List<RawBeans>> getAllRawBeans() {
        return mAllRawBeans;
    }

    void insert(RawBeans rawbeans) {
        mRepository.insert(rawbeans);
    }
}
