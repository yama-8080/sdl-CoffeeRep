package ymz.coffeerep.scenes.edit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansEditViewModel extends AndroidViewModel {

    private RoastBeansRepository _repository;

    final int WRONG_AMOUNT = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<RoastBeans> complete = new MutableLiveData<>(null);

    //constructor
    public RoastBeansEditViewModel(Application application) {
        super(application);
        _repository = new RoastBeansRepository(application);
    }

    void update(RoastBeans newRoastbeans) {
        //name must be non-null
        if(newRoastbeans.getRoastbeans_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(newRoastbeans.getRoastbeans_amount() == WRONG_AMOUNT){
            errorMsg.setValue("内容量には自然数を入力してください");
        }
        else{
            try{
                RoastBeans updated = _repository.update(newRoastbeans);
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
