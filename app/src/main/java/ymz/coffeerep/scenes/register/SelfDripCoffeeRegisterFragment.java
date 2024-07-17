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
import ymz.coffeerep.data.dropdown.DripMethodItem;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.databinding.FragmentSelfdripcoffeeRegisterBinding;

public class SelfDripCoffeeRegisterFragment extends Fragment {

    private FragmentSelfdripcoffeeRegisterBinding _binding;
    private SelfDripCoffeeRegisterViewModel _vm;

    //constructor
    public SelfDripCoffeeRegisterFragment() {
        super(R.layout.fragment_selfdripcoffee_register);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentSelfdripcoffeeRegisterBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentSelfdripcoffeeRegisterBinding.bind(view);

        _vm = new ViewModelProvider(this).get(SelfDripCoffeeRegisterViewModel.class);

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
        _binding.insertFabSelfdripcoffeeRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _vm.insert(createNewSelfDripCoffee());
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
        //for roastbeans
        ArrayAdapter<String> roastbeansAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        roastbeansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _vm.getAllRoastBeans().observe(getViewLifecycleOwner(), roastbeans -> {
            //LiveData does not have a value unless it has an observer
            _vm.setRoastbeansItemsToAdapter(roastbeansAdapter, roastbeans);
        });
        _binding.roastbeansSpinnerSelfdripcoffeeRegister.setAdapter(roastbeansAdapter);

        //for dripmethod
        ArrayAdapter<String> dripmethodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        dripmethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DripMethodItem.setItemsToAdapter(dripmethodAdapter);
        _binding.dripmethodSpinnerSelfdripcoffeeRegister.setAdapter(dripmethodAdapter);

        //for drink_method_temperature
        ArrayAdapter<String> drinkMethodTempAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTemperatureItem.setItemsToAdapter(drinkMethodTempAdapter);
        _binding.drinkMethodTemperatureSpinnerSelfdripcoffeeRegister.setAdapter(drinkMethodTempAdapter);

        //for drink_method_type
        ArrayAdapter<String> drinkMethodTypeAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTypeItem.setItemsToAdapter(drinkMethodTypeAdapter);
        _binding.drinkMethodTypeSpinnerSelfdripcoffeeRegister.setAdapter(drinkMethodTypeAdapter);

        //for flavor
        ArrayAdapter<String> flavorAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FlavorItem.setItemsToAdapter(flavorAdapter);
        _binding.flavorSpinnerSelfdripcoffeeRegister.setAdapter(flavorAdapter);

        //for match_food
        ArrayAdapter<String> matchFoodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        matchFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MatchFoodItem.setItemsToAdapter(matchFoodAdapter);
        _binding.matchFoodSpinnerSelfdripcoffeeRegister.setAdapter(matchFoodAdapter);

        //for atmosphere
        ArrayAdapter<String> atmosphereAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        atmosphereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AtmosphereItem.setItemsToAdapter(atmosphereAdapter);
        _binding.atmosphereSpinnerSelfdripcoffeeRegister.setAdapter(atmosphereAdapter);
    }

    //create new entity
    private SelfDripCoffee createNewSelfDripCoffee(){
        SelfDripCoffee newSelfdripcoffee = new SelfDripCoffee();

        newSelfdripcoffee.setSelfdripcoffee_id(0);  //id 0 means initial
        newSelfdripcoffee.setRegistered_time(System.currentTimeMillis());
        newSelfdripcoffee.setSelfdripcoffee_name(_binding.nameSelfdripcoffeeRegister.getText().toString());
        newSelfdripcoffee.setSelfdripcoffee_drink_date(_binding.drinkDateSelfdripcoffeeRegister.getText().toString());
        newSelfdripcoffee.setSelfdripcoffee_drink_place(_binding.drinkPlaceSelfdripcoffeeRegister.getText().toString());

        if(_binding.drinkAmountSelfdripcoffeeRegister.getText().toString().isEmpty())
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(0);
        else if(_vm.isNumeric(_binding.drinkAmountSelfdripcoffeeRegister.getText().toString()))
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(Integer.valueOf(_binding.drinkAmountSelfdripcoffeeRegister.getText().toString()));
        else
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceSelfdripcoffeeRegister.getText().toString().isEmpty())
            newSelfdripcoffee.setSelfdripcoffee_price(0);
        else if(_vm.isNumeric(_binding.priceSelfdripcoffeeRegister.getText().toString()))
            newSelfdripcoffee.setSelfdripcoffee_price(Integer.valueOf(_binding.priceSelfdripcoffeeRegister.getText().toString()));
        else
            newSelfdripcoffee.setSelfdripcoffee_price(_vm.WRONG_PRICE);

        if(_binding.roastbeansSpinnerSelfdripcoffeeRegister.getSelectedItemPosition() >= 0){
            newSelfdripcoffee.setSelfdripcoffee_roastbeans(
                    _vm.positionToId(
                            _binding.roastbeansSpinnerSelfdripcoffeeRegister.getSelectedItemPosition()
                    )
            );
        }

        newSelfdripcoffee.setSelfdripcoffee_dripmethod(_binding.dripmethodSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_drink_method_temperature(_binding.drinkMethodTemperatureSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_drink_method_type(_binding.drinkMethodTypeSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());

        newSelfdripcoffee.setSelfdripcoffee_taste_sweet(_binding.tasteSweetSeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_bitter(_binding.tasteBitterSeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_sour(_binding.tasteSourSeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_scent(_binding.tasteScentSeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_body(_binding.tasteBodySeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_rich(_binding.tasteRichSeekBarSelfdripcoffeeRegister.getProgress());

        newSelfdripcoffee.setSelfdripcoffee_flavor(_binding.flavorSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_match_food(_binding.matchFoodSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_atmosphere(_binding.atmosphereSpinnerSelfdripcoffeeRegister.getSelectedItemPosition());

        newSelfdripcoffee.setSelfdripcoffee_caffeineless(_binding.caffeinelessCheckboxSelfdripcoffeeRegister.isChecked());
        newSelfdripcoffee.setSelfdripcoffee_review(_binding.reviewSeekBarSelfdripcoffeeRegister.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_memo(_binding.memoSelfdripcoffeeRegister.getText().toString());

        return newSelfdripcoffee;
    }
}
