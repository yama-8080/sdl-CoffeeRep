package ymz.coffeerep.scenes.edit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansEditViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RawBeansEditViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }

    void update(RawBeans newRawbeans) {
        //name must be non-null
        if(newRawbeans.getRawbeans_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else{
            try{
                _repository.update(newRawbeans);
                complete.setValue(true);
            }catch (Exception e){
                errorMsg.setValue(e.getMessage());
            }
        }
    }
}