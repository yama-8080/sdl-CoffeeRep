package ymz.coffeerep.scenes.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import ymz.coffeerep.R;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.databinding.FragmentRawbeansRegisterBinding;
import ymz.coffeerep.data.dropdown.VarietyItem;

public class RawBeansRegisterFragment extends Fragment {

    private FragmentRawbeansRegisterBinding _binding;
    private RawBeansRegisterViewModel _vm;

    //constructor
    public RawBeansRegisterFragment() {
        super(R.layout.fragment_rawbeans_register);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansRegisterBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansRegisterBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansRegisterViewModel.class);

        spinnerConfig();

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to list-fragment when complete insert
        _vm.complete.observe(getViewLifecycleOwner(), complete -> {
            if(complete){
                Navigation.findNavController(view).popBackStack();
            }
        });

        //insert button listener
        _binding.insertFabRawbeansRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _vm.insert(createNewRawBeans());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    //configure spinner
    private void spinnerConfig(){
        //for variety
        ArrayAdapter<String> varietyAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VarietyItem.setItemsToAdapter(varietyAdapter);
        _binding.varietySpinnerRawbeansRegister.setAdapter(varietyAdapter);

        //for process
        ArrayAdapter<String> processAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        processAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProcessItem.setItemsToAdapter(processAdapter);
        _binding.processSpinnerRawbeansRegister.setAdapter(processAdapter);
    }

    //create new entity
    private RawBeans createNewRawBeans(){
        RawBeans newRawbeans = new RawBeans();

        newRawbeans.setRawbeans_id(0);  //id 0 means initial
        newRawbeans.setRegistered_time(System.currentTimeMillis());
        newRawbeans.setRawbeans_name(_binding.nameRawbeansRegister.getText().toString());
        newRawbeans.setRawbeans_purchased_date(_binding.purchasedDateRawbeansRegister.getText().toString());
        newRawbeans.setRawbeans_purchased_shop(_binding.purchasedShopRawbeansRegister.getText().toString());

        if(_binding.amountRawbeansRegister.getText().toString().isEmpty())
            newRawbeans.setRawbeans_amount(0);
        else if(_vm.isNumeric(_binding.amountRawbeansRegister.getText().toString()))
            newRawbeans.setRawbeans_amount(Integer.valueOf(_binding.amountRawbeansRegister.getText().toString()));
        else
            newRawbeans.setRawbeans_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceRawbeansRegister.getText().toString().isEmpty())
            newRawbeans.setRawbeans_price(0);
        else if(_vm.isNumeric(_binding.priceRawbeansRegister.getText().toString()))
            newRawbeans.setRawbeans_price(Integer.valueOf(_binding.priceRawbeansRegister.getText().toString()));
        else
            newRawbeans.setRawbeans_price(_vm.WRONG_PRICE);

        newRawbeans.setRawbeans_country(_binding.countryRawbeansRegister.getText().toString());
        newRawbeans.setRawbeans_place(_binding.placeRawbeansRegister.getText().toString());
        newRawbeans.setRawbeans_farm(_binding.farmRawbeansRegister.getText().toString());
        newRawbeans.setRawbeans_variety(_binding.varietySpinnerRawbeansRegister.getSelectedItemPosition());
        newRawbeans.setRawbeans_process(_binding.processSpinnerRawbeansRegister.getSelectedItemPosition());
        newRawbeans.setRawbeans_caffeineless(_binding.caffeinelessCheckboxRawbeansRegister.isChecked());
        newRawbeans.setRawbeans_review(_binding.reviewSeekBarRawbeansRegister.getProgress());
        newRawbeans.setRawbeans_memo(_binding.memoRawbeansRegister.getText().toString());

        return newRawbeans;
    }
}
