package ymz.coffeerep.scenes.edit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.repository.readymadecoffee.ReadyMadeCoffeeRepository;

public class ReadyMadeCoffeeEditViewModel extends AndroidViewModel {

    private ReadyMadeCoffeeRepository _repository;

    final int WRONG_AMOUNT = -1;
    final int WRONG_PRICE = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<ReadyMadeCoffee> complete = new MutableLiveData<>(null);

    //constructor
    public ReadyMadeCoffeeEditViewModel(Application application) {
        super(application);
        _repository = new ReadyMadeCoffeeRepository(application);
    }

    void update(ReadyMadeCoffee newReadymadecoffee) {
        //name must be non-null
        if(newReadymadecoffee.getReadymadecoffee_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(newReadymadecoffee.getReadymadecoffee_drink_amount() == WRONG_AMOUNT){
            errorMsg.setValue("飲んだ量には整数値を入力してください");
        }
        else if(newReadymadecoffee.getReadymadecoffee_price() == WRONG_PRICE){
            errorMsg.setValue("価格には整数値を入力してください");
        }
        else{
            try{
                ReadyMadeCoffee updated = _repository.update(newReadymadecoffee);
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
