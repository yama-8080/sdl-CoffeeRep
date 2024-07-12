package ymz.coffeerep.scenes.insert;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansInsertViewModel extends AndroidViewModel {

    private RawBeansRepository mRepository;

    //constructor
    public RawBeansInsertViewModel(Application application) {
        super(application);
        mRepository = new RawBeansRepository(application);
    }

    void insert(RawBeans rawbeans) {
        mRepository.insert(rawbeans);
    }
}
