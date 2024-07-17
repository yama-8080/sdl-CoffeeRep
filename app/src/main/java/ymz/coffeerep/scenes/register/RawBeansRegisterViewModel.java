package ymz.coffeerep.scenes.register;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;

public class RawBeansRegisterViewModel extends AndroidViewModel {

    private RawBeansRepository _repository;

    final int WRONG_AMOUNT = -1;
    final int WRONG_PRICE = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RawBeansRegisterViewModel(Application application) {
        super(application);
        _repository = new RawBeansRepository(application);
    }

    void insert(RawBeans rawbeans) {
        //name must be non-null
        if(rawbeans.getRawbeans_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(rawbeans.getRawbeans_amount() == WRONG_AMOUNT){
            errorMsg.setValue("内容量には整数値を入力してください");
        }
        else if(rawbeans.getRawbeans_price() == WRONG_PRICE){
            errorMsg.setValue("価格には整数値を入力してください");
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

    boolean isNumeric(String str){
        for(int i=0; i<str.length(); i++)
            if(!Character.isDigit(str.charAt(i)))
                return false;

        return true;
    }
}
