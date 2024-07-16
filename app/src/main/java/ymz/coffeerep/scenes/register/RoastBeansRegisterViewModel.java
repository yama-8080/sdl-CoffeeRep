package ymz.coffeerep.scenes.register;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.repository.rawbeans.RawBeansRepository;
import ymz.coffeerep.repository.roastbeans.RoastBeansRepository;

public class RoastBeansRegisterViewModel extends AndroidViewModel {

    private RoastBeansRepository _roastRep;
    private RawBeansRepository _rawRep;
    private final LiveData<List<RawBeans>> _allRawBeans;
    private List<Integer> _allRawBeansId;

    final int WRONG_AMOUNT = -1;

    //MutableLiveData must be non-null (observed by fragment)
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>("");
    protected MutableLiveData<Boolean> complete = new MutableLiveData<>(false);

    //constructor
    public RoastBeansRegisterViewModel(Application application) {
        super(application);
        _roastRep = new RoastBeansRepository(application);
        _rawRep = new RawBeansRepository(application);
        _allRawBeans = _rawRep.getAllRawBeans();
        _allRawBeansId = new ArrayList<Integer>();
    }

    //set items to dropdown list
    void setRoastRawbeansItemsToAdapter(ArrayAdapter<String> adapter, List<RawBeans> rawbeans){
        Iterator<RawBeans> iterator = rawbeans.iterator();
        while (iterator.hasNext()) {
            RawBeans item = iterator.next();
            adapter.add(item.getRawbeans_name());
            _allRawBeansId.add(item.getRawbeans_id());
        }
    }

    //change position in rawbeans dropdown list  into rawbeans_id
    int positionToId(int pos){
        return _allRawBeansId.get(pos);
    }

    //get rawbeans list from repository and return it directly
    LiveData<List<RawBeans>> getAllRawBeans() {
        return _allRawBeans;
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
                _roastRep.insert(roastbeans);
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