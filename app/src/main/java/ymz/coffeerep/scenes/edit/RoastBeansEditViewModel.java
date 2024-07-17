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
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansEditViewModel extends AndroidViewModel {

    private RoastBeansRepository _roastRep;
    private RawBeansRepository _rawRep;
    private final LiveData<List<RawBeans>> _allRawBeans;
    private List<Integer> _allRawBeansId;

    final int WRONG_AMOUNT = -1;
    final int WRONG_PRICE = -1;
    final int ID_NOT_FOUND = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<RoastBeans> complete = new MutableLiveData<>(null);
    protected MutableLiveData<Boolean> makeOutArray = new MutableLiveData<>(false);

    //constructor
    public RoastBeansEditViewModel(Application application) {
        super(application);
        _roastRep = new RoastBeansRepository(application);
        _rawRep = new RawBeansRepository(application);
        _allRawBeans = _rawRep.getAllRawBeans();
        _allRawBeansId = new ArrayList<Integer>();
    }

    //set items to dropdown list
    void setRoastRawbeansItemsToAdapter(ArrayAdapter<String> adapter, List<RawBeans> rawbeans){
        //default item
        adapter.add(DropDownDefault.getDefault_str());
        _allRawBeansId.add(DropDownDefault.getDefault_id());

        //read all items
        Iterator<RawBeans> iterator = rawbeans.iterator();
        while (iterator.hasNext()) {
            RawBeans item = iterator.next();
            adapter.add(item.getRawbeans_name());
            _allRawBeansId.add(item.getRawbeans_id());
        }

        //send set-completed message
        makeOutArray.setValue(true);
    }

    //transform rawbeans_id into position in rawbeans dropdown list
    int idToPosition(int id){
        int idx = 0;
        Iterator<Integer> iterator = _allRawBeansId.iterator();
        while (iterator.hasNext()) {
            int idInList = iterator.next();
            if (id == idInList)
                return idx;
            idx++;
        }
        return ID_NOT_FOUND;
    }

    //transform position in rawbeans dropdown list into rawbeans_id
    int positionToId(int pos){
        return _allRawBeansId.get(pos);
    }

    //get rawbeans list from repository and return it directly
    LiveData<List<RawBeans>> getAllRawBeans() {
        return _allRawBeans;
    }

    void update(RoastBeans newRoastbeans) {
        //name must be non-null
        if(newRoastbeans.getRoastbeans_name().trim().isEmpty()){
            errorMsg.setValue("名前を入力してください");
        }
        else if(newRoastbeans.getRoastbeans_amount() == WRONG_AMOUNT){
            errorMsg.setValue("内容量には整数値を入力してください");
        }
        else if(newRoastbeans.getRoastbeans_price() == WRONG_PRICE){
            errorMsg.setValue("価格には整数値を入力してください");
        }
        else{
            try{
                RoastBeans updated = _roastRep.update(newRoastbeans);
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
