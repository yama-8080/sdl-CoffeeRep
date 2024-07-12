package ymz.coffeerep.scenes.insert;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansInsertViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;

    //constructor
    public RawBeansInsertViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }

    void insert(RawBeans rawbeans) {
        _repository.insert(rawbeans);
    }
}
