package ymz.coffeerep.scenes.register;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansRegisterViewModel extends AndroidViewModel {

    private RoastBeansRepository _repository;

    final int WRONG_AMOUNT = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RoastBeansRegisterViewModel(Application application) {
        super(application);
        _repository = new RoastBeansRepository(application);
    }

    void insert(RoastBeans roastbeans) {
        //name must be non-null
        if(roastbeans.getRoastbeans_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(roastbeans.getRoastbeans_amount() == WRONG_AMOUNT){
            errorMsg.setValue("内容量には自然数を入力してください");
        }
        else{
            try{
                _repository.insert(roastbeans);
                complete.setValue(true);
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