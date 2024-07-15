package ymz.coffeerep.scenes.edit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansEditViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;

    final int WRONG_AMOUNT = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<RawBeans> complete = new MutableLiveData<>(null);

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
        else if(newRawbeans.getRawbeans_amount() == WRONG_AMOUNT){
            errorMsg.setValue("内容量には自然数を入力してください");
        }
        else{
            try{
                RawBeans updated = _repository.update(newRawbeans);
                complete.setValue(updated);
            }catch (Exception e){
                errorMsg.setValue(e.getMessage());
            }
        }
    }

    boolean isNumeric(String str){
        for(int i=0; i<str.length(); i++)
            if(!Character.isDigit(str.charAt(i)))
                return false;

        return true;
    }
}