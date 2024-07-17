package ymz.coffeerep.scenes.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Iterator;
import java.util.List;

import ymz.coffeerep.data.dropdown.DropDownDefault;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.repository.readymadecoffee.ReadyMadeCoffeeRepository;

public class ReadyMadeCoffeeDetailViewModel extends AndroidViewModel {

    private ReadyMadeCoffeeRepository _redRep;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> completeDelete = new MutableLiveData<>(false);

    //constructor
    public ReadyMadeCoffeeDetailViewModel(Application application) {
        super(application);
        _redRep = new ReadyMadeCoffeeRepository(application);
    }

    void delete(ReadyMadeCoffee readymadecoffee) {
        try{
            _redRep.delete(readymadecoffee);
            completeDelete.setValue(true);
        }catch (Exception e){
            errorMsg.setValue(e.getMessage());
        }
    }
}
