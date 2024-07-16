package ymz.coffeerep.scenes.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ymz.coffeerep.R;
import ymz.coffeerep.data.dropdown.BrendItem;
import ymz.coffeerep.data.dropdown.RoastLevelItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.databinding.FragmentRoastbeansRegisterBinding;

public class RoastBeansRegisterFragment extends Fragment {

    private FragmentRoastbeansRegisterBinding _binding;
    private RoastBeansRegisterViewModel _vm;

    //constructor
    public RoastBeansRegisterFragment() {
        super(R.layout.fragment_roastbeans_register);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRoastbeansRegisterBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRoastbeansRegisterBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RoastBeansRegisterViewModel.class);

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
        _binding.insertFabRoastbeansRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _vm.insert(createNewRoastBeans());
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
        //for brend
        ArrayAdapter<String> brendAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        brendAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BrendItem.setItemsToAdapter(brendAdapter);
        _binding.brendSpinnerRoastbeansRegister.setAdapter(brendAdapter);

        //for roastlevel
        ArrayAdapter<String> roastlevelAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        roastlevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RoastLevelItem.setItemsToAdapter(roastlevelAdapter);
        _binding.roastLevelSpinnerRoastbeansRegister.setAdapter(roastlevelAdapter);

        //for roast_rawbeans
        ArrayAdapter<String> roastRawbeansAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        roastRawbeansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _vm.getAllRawBeans().observe(getViewLifecycleOwner(), rawbeans -> {
            //LiveData does not have a value unless it has an observer
            _vm.setRoastRawbeansItemsToAdapter(roastRawbeansAdapter, rawbeans);
        });
        _binding.roastRawbeansSpinnerRoastbeansRegister.setAdapter(roastRawbeansAdapter);

        //for variety
        ArrayAdapter<String> varietyAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VarietyItem.setItemsToAdapter(varietyAdapter);
        _binding.varietySpinnerRoastbeansRegister.setAdapter(varietyAdapter);

        //for process
        ArrayAdapter<String> processAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        processAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProcessItem.setItemsToAdapter(processAdapter);
        _binding.processSpinnerRoastbeansRegister.setAdapter(processAdapter);
    }

    //create new entity
    private RoastBeans createNewRoastBeans(){
        RoastBeans newRoastbeans = new RoastBeans();

        newRoastbeans.setRoastbeans_id(0);  //id 0 means initial
        newRoastbeans.setRegistered_time(System.currentTimeMillis());
        newRoastbeans.setRoastbeans_name(_binding.nameRoastbeansRegister.getText().toString());
        newRoastbeans.setRoastbeans_purchased_date(_binding.purchasedDateRoastbeansRegister.getText().toString());
        newRoastbeans.setRoastbeans_purchased_shop(_binding.purchasedShopRoastbeansRegister.getText().toString());

        if(_binding.amountRoastbeansRegister.getText().toString().isEmpty())
            newRoastbeans.setRoastbeans_amount(0);
        else if(_vm.isNumeric(_binding.amountRoastbeansRegister.getText().toString()))
            newRoastbeans.setRoastbeans_amount(Integer.valueOf(_binding.amountRoastbeansRegister.getText().toString()));
        else
            newRoastbeans.setRoastbeans_amount(_vm.WRONG_AMOUNT);

        newRoastbeans.setRoastbeans_brend(_binding.brendSpinnerRoastbeansRegister.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_self_roast(_binding.selfRoastCheckboxRoastbeansRegister.isChecked());

        if(_binding.roastRawbeansSpinnerRoastbeansRegister.getSelectedItemPosition() >= 0){
            newRoastbeans.setRoastbeans_roast_rawbeans_id(
                    _vm.positionToId(
                            _binding.roastRawbeansSpinnerRoastbeansRegister.getSelectedItemPosition()
                    )
            );
        }

        newRoastbeans.setRoastbeans_roast_level(_binding.roastLevelSpinnerRoastbeansRegister.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_country(_binding.countryRoastbeansRegister.getText().toString());
        newRoastbeans.setRoastbeans_place(_binding.placeRoastbeansRegister.getText().toString());
        newRoastbeans.setRoastbeans_farm(_binding.farmRoastbeansRegister.getText().toString());
        newRoastbeans.setRoastbeans_variety(_binding.varietySpinnerRoastbeansRegister.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_process(_binding.processSpinnerRoastbeansRegister.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_caffeineless(_binding.caffeinelessCheckboxRoastbeansRegister.isChecked());
        newRoastbeans.setRoastbeans_review(_binding.reviewSeekBarRoastbeansRegister.getProgress());
        newRoastbeans.setRoastbeans_memo(_binding.memoRoastbeansRegister.getText().toString());

        return newRoastbeans;
    }
}
