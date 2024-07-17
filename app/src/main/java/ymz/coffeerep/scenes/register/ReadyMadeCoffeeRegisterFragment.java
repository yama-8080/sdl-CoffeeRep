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
import ymz.coffeerep.data.dropdown.AtmosphereItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTemperatureItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTypeItem;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.databinding.FragmentReadymadecoffeeRegisterBinding;

public class ReadyMadeCoffeeRegisterFragment extends Fragment {

    private FragmentReadymadecoffeeRegisterBinding _binding;
    private ReadyMadeCoffeeRegisterViewModel _vm;

    //constructor
    public ReadyMadeCoffeeRegisterFragment() {
        super(R.layout.fragment_readymadecoffee_register);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentReadymadecoffeeRegisterBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentReadymadecoffeeRegisterBinding.bind(view);

        _vm = new ViewModelProvider(this).get(ReadyMadeCoffeeRegisterViewModel.class);

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
        _binding.insertFabReadymadecoffeeRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _vm.insert(createNewReadyMadeCoffee());
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
        //for drink_method_temperature
        ArrayAdapter<String> drinkMethodTempAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTemperatureItem.setItemsToAdapter(drinkMethodTempAdapter);
        _binding.drinkMethodTemperatureSpinnerReadymadecoffeeRegister.setAdapter(drinkMethodTempAdapter);

        //for drink_method_type
        ArrayAdapter<String> drinkMethodTypeAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTypeItem.setItemsToAdapter(drinkMethodTypeAdapter);
        _binding.drinkMethodTypeSpinnerReadymadecoffeeRegister.setAdapter(drinkMethodTypeAdapter);

        //for flavor
        ArrayAdapter<String> flavorAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FlavorItem.setItemsToAdapter(flavorAdapter);
        _binding.flavorSpinnerReadymadecoffeeRegister.setAdapter(flavorAdapter);

        //for match_food
        ArrayAdapter<String> matchFoodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        matchFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MatchFoodItem.setItemsToAdapter(matchFoodAdapter);
        _binding.matchFoodSpinnerReadymadecoffeeRegister.setAdapter(matchFoodAdapter);

        //for atmosphere
        ArrayAdapter<String> atmosphereAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        atmosphereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AtmosphereItem.setItemsToAdapter(atmosphereAdapter);
        _binding.atmosphereSpinnerReadymadecoffeeRegister.setAdapter(atmosphereAdapter);
    }

    //create new entity
    private ReadyMadeCoffee createNewReadyMadeCoffee(){
        ReadyMadeCoffee newReadymadecoffee = new ReadyMadeCoffee();

        newReadymadecoffee.setReadymadecoffee_id(0);  //id 0 means initial
        newReadymadecoffee.setRegistered_time(System.currentTimeMillis());
        newReadymadecoffee.setReadymadecoffee_name(_binding.nameReadymadecoffeeRegister.getText().toString());
        newReadymadecoffee.setReadymadecoffee_drink_date(_binding.drinkDateReadymadecoffeeRegister.getText().toString());
        newReadymadecoffee.setReadymadecoffee_drink_place(_binding.drinkPlaceReadymadecoffeeRegister.getText().toString());

        if(_binding.drinkAmountReadymadecoffeeRegister.getText().toString().isEmpty())
            newReadymadecoffee.setReadymadecoffee_drink_amount(0);
        else if(_vm.isNumeric(_binding.drinkAmountReadymadecoffeeRegister.getText().toString()))
            newReadymadecoffee.setReadymadecoffee_drink_amount(Integer.valueOf(_binding.drinkAmountReadymadecoffeeRegister.getText().toString()));
        else
            newReadymadecoffee.setReadymadecoffee_drink_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceReadymadecoffeeRegister.getText().toString().isEmpty())
            newReadymadecoffee.setReadymadecoffee_price(0);
        else if(_vm.isNumeric(_binding.priceReadymadecoffeeRegister.getText().toString()))
            newReadymadecoffee.setReadymadecoffee_price(Integer.valueOf(_binding.priceReadymadecoffeeRegister.getText().toString()));
        else
            newReadymadecoffee.setReadymadecoffee_price(_vm.WRONG_PRICE);

        newReadymadecoffee.setReadymadecoffee_drink_method_temperature(_binding.drinkMethodTemperatureSpinnerReadymadecoffeeRegister.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_drink_method_type(_binding.drinkMethodTypeSpinnerReadymadecoffeeRegister.getSelectedItemPosition());

        newReadymadecoffee.setReadymadecoffee_taste_sweet(_binding.tasteSweetSeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_bitter(_binding.tasteBitterSeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_sour(_binding.tasteSourSeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_scent(_binding.tasteScentSeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_body(_binding.tasteBodySeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_rich(_binding.tasteRichSeekBarReadymadecoffeeRegister.getProgress());

        newReadymadecoffee.setReadymadecoffee_flavor(_binding.flavorSpinnerReadymadecoffeeRegister.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_match_food(_binding.matchFoodSpinnerReadymadecoffeeRegister.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_atmosphere(_binding.atmosphereSpinnerReadymadecoffeeRegister.getSelectedItemPosition());

        newReadymadecoffee.setReadymadecoffee_caffeineless(_binding.caffeinelessCheckboxReadymadecoffeeRegister.isChecked());
        newReadymadecoffee.setReadymadecoffee_review(_binding.reviewSeekBarReadymadecoffeeRegister.getProgress());
        newReadymadecoffee.setReadymadecoffee_memo(_binding.memoReadymadecoffeeRegister.getText().toString());

        return newReadymadecoffee;
    }
}
