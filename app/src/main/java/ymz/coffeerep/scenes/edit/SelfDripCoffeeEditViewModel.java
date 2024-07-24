package ymz.coffeerep.scenes.edit;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ymz.coffeerep.data.dropdown.DropDownDefault;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;
import ymz.coffeerep.repository.selfdripcoffee.SelfDripCoffeeRepository;

public class SelfDripCoffeeEditViewModel extends AndroidViewModel {

    private SelfDripCoffeeRepository _selfRep;
    private RoastBeansRepository _roastRep;
    private final LiveData<List<RoastBeans>> _allRoastBeans;
    private List<Integer> _allRoastBeansId;

    final int WRONG_AMOUNT = -1;
    final int WRONG_PRICE = -1;
    final int ID_NOT_FOUND = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<SelfDripCoffee> complete = new MutableLiveData<>(null);
    protected MutableLiveData<Boolean> makeOutArray = new MutableLiveData<>(false);

    //constructor
    public SelfDripCoffeeEditViewModel(Application application) {
        super(application);
        _selfRep = new SelfDripCoffeeRepository(application);
        _roastRep = new RoastBeansRepository(application);
        _allRoastBeans = _roastRep.getAllRoastBeans();
        _allRoastBeansId = new ArrayList<Integer>();
    }

    //set items to dropdown list
    void setRoastbeansItemsToAdapter(ArrayAdapter<String> adapter, List<RoastBeans> roastbeans){
        //default item
        adapter.add(DropDownDefault.getDefault_str());
        _allRoastBeansId.add(DropDownDefault.getDefault_id());

        //read all items
        Iterator<RoastBeans> iterator = roastbeans.iterator();
        while (iterator.hasNext()) {
            RoastBeans item = iterator.next();
            adapter.add(item.getRoastbeans_name());
            _allRoastBeansId.add(item.getRoastbeans_id());
        }

        //send set-completed message
        makeOutArray.setValue(true);
    }

    //transform roastbeans_id into position in roastbeans dropdown list
    int idToPosition(int id){
        int idx = 0;
        Iterator<Integer> iterator = _allRoastBeansId.iterator();
        while (iterator.hasNext()) {
            int idInList = iterator.next();
            if (id == idInList)
                return idx;
            idx++;
        }
        return ID_NOT_FOUND;
    }

    //transform position in roastbeans dropdown list into roastbeans_id
    int positionToId(int pos){
        return _allRoastBeansId.get(pos);
    }

    //get roastbeans list from repository and return it directly
    LiveData<List<RoastBeans>> getAllRoastBeans() {
        return _allRoastBeans;
    }

    void update(SelfDripCoffee newSelfdripcoffee) {
        //name must be non-null
        if(newSelfdripcoffee.getSelfdripcoffee_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(newSelfdripcoffee.getSelfdripcoffee_drink_amount() == WRONG_AMOUNT){
            errorMsg.setValue("飲んだ量には整数値を入力してください");
        }
        else if(newSelfdripcoffee.getSelfdripcoffee_price() == WRONG_PRICE){
            errorMsg.setValue("価格には整数値を入力してください");
        }
        else{
            try{
                SelfDripCoffee updated = _selfRep.update(newSelfdripcoffee);
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
