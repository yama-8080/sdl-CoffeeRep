package ymz.coffeerep.scenes.insert;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansInsertViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RawBeansInsertViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }

    void insert(RawBeans rawbeans) {
        //name must be non-null
        if(rawbeans.getRawBeansName().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else{
            try{
                _repository.insert(rawbeans);
                complete.setValue(true);
            }catch (Exception e){
                errorMsg.setValue(e.getMessage());
            }
        }
    }
}
