package ymz.coffeerep.scenes.edit;

import android.os.Bundle;
import android.util.Log;
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
import ymz.coffeerep.data.dropdown.BrendItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTemperatureItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTypeItem;
import ymz.coffeerep.data.dropdown.DripMethodItem;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.RoastLevelItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.databinding.FragmentSelfdripcoffeeEditBinding;

public class SelfDripCoffeeEditFragment extends Fragment {

    private FragmentSelfdripcoffeeEditBinding _binding;
    private SelfDripCoffeeEditViewModel _vm;

    //constructor
    public SelfDripCoffeeEditFragment() {
        super(R.layout.fragment_selfdripcoffee_edit);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentSelfdripcoffeeEditBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentSelfdripcoffeeEditBinding.bind(view);

        _vm = new ViewModelProvider(this).get(SelfDripCoffeeEditViewModel.class);

        spinnerConfig();

        //get specific item by safe-args
        SelfDripCoffee selectedSelfdripcoffee = SelfDripCoffeeEditFragmentArgs.fromBundle(getArguments()).getSpecSelfdripcoffee();
        showDefaultDetail(selectedSelfdripcoffee);
        Log.d("YMZdebug", "[SelfDripCoffeeEditFragment.onViewCreated]: ID is " + selectedSelfdripcoffee.getSelfdripcoffee_id());

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //update
        _binding.updateFabSelfdripcoffeeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update(selectedSelfdripcoffee);
            }
        });

        //go back to detail-fragment when complete update
        _vm.complete.observe(getViewLifecycleOwner(), updatedSelfdripcoffee -> {
            if(updatedSelfdripcoffee != null){
                Bundle result = new Bundle();
                result.putSerializable("bundleKey_update_selfdripcoffee", updatedSelfdripcoffee);
                getParentFragmentManager().setFragmentResult("requestKey_update_selfdripcoffee", result);
                Navigation.findNavController(view).popBackStack();
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
        _binding.roastbeansSpinnerSelfdripcoffeeEdit.setAdapter(roastbeansAdapter);

        //for dripmethod
        ArrayAdapter<String> dripmethodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        dripmethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DripMethodItem.setItemsToAdapter(dripmethodAdapter);
        _binding.dripmethodSpinnerSelfdripcoffeeEdit.setAdapter(dripmethodAdapter);

        //for drink_method_temperature
        ArrayAdapter<String> drinkMethodTempAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTemperatureItem.setItemsToAdapter(drinkMethodTempAdapter);
        _binding.drinkMethodTemperatureSpinnerSelfdripcoffeeEdit.setAdapter(drinkMethodTempAdapter);

        //for drink_method_type
        ArrayAdapter<String> drinkMethodTypeAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTypeItem.setItemsToAdapter(drinkMethodTypeAdapter);
        _binding.drinkMethodTypeSpinnerSelfdripcoffeeEdit.setAdapter(drinkMethodTypeAdapter);

        //for flavor
        ArrayAdapter<String> flavorAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FlavorItem.setItemsToAdapter(flavorAdapter);
        _binding.flavorSpinnerSelfdripcoffeeEdit.setAdapter(flavorAdapter);

        //for match_food
        ArrayAdapter<String> matchFoodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        matchFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MatchFoodItem.setItemsToAdapter(matchFoodAdapter);
        _binding.matchFoodSpinnerSelfdripcoffeeEdit.setAdapter(matchFoodAdapter);

        //for atmosphere
        ArrayAdapter<String> atmosphereAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        atmosphereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AtmosphereItem.setItemsToAdapter(atmosphereAdapter);
        _binding.atmosphereSpinnerSelfdripcoffeeEdit.setAdapter(atmosphereAdapter);
    }

    private void showDefaultDetail(SelfDripCoffee selfdripcoffee){
        _binding.nameSelfdripcoffeeEdit.setText(selfdripcoffee.getSelfdripcoffee_name());
        _binding.drinkDateSelfdripcoffeeEdit.setText(selfdripcoffee.getSelfdripcoffee_drink_date());
        _binding.drinkPlaceSelfdripcoffeeEdit.setText(selfdripcoffee.getSelfdripcoffee_drink_place());
        _binding.drinkAmountSelfdripcoffeeEdit.setText(Integer.toString(selfdripcoffee.getSelfdripcoffee_drink_amount()));
        _binding.priceSelfdripcoffeeEdit.setText(Integer.toString(selfdripcoffee.getSelfdripcoffee_price()));

        //avoid from executed this before vm make position-rawbeans_id array
        _vm.makeOutArray.observe(getViewLifecycleOwner(), makeOutArray -> {
            if(makeOutArray){
                _binding.roastbeansSpinnerSelfdripcoffeeEdit.setSelection(
                        _vm.idToPosition(
                                selfdripcoffee.getSelfdripcoffee_roastbeans()
                        )
                );
            }
        });

        _binding.dripmethodSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_dripmethod());
        _binding.drinkMethodTemperatureSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_drink_method_temperature());
        _binding.drinkMethodTypeSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_drink_method_type());

        _binding.tasteSweetSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_sweet());
        _binding.tasteBitterSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_bitter());
        _binding.tasteSourSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_sour());
        _binding.tasteScentSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_scent());
        _binding.tasteBodySeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_body());
        _binding.tasteRichSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_taste_rich());

        _binding.flavorSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_flavor());
        _binding.matchFoodSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_match_food());
        _binding.atmosphereSpinnerSelfdripcoffeeEdit.setSelection(selfdripcoffee.getSelfdripcoffee_atmosphere());

        _binding.caffeinelessCheckboxSelfdripcoffeeEdit.setChecked(selfdripcoffee.getSelfdripcoffee_caffeineless());
        _binding.reviewSeekBarSelfdripcoffeeEdit.setProgress(selfdripcoffee.getSelfdripcoffee_review());
        _binding.memoSelfdripcoffeeEdit.setText(selfdripcoffee.getSelfdripcoffee_memo());
    }

    private void update(SelfDripCoffee oldSelfdripcoffee) {
        _vm.update(createNewSelfDripCoffee(oldSelfdripcoffee));
    }

    //create new entity
    private SelfDripCoffee createNewSelfDripCoffee(SelfDripCoffee oldSelfdripcoffee){
        SelfDripCoffee newSelfdripcoffee = new SelfDripCoffee();

        newSelfdripcoffee.setSelfdripcoffee_id(oldSelfdripcoffee.getSelfdripcoffee_id());
        newSelfdripcoffee.setRegistered_time(oldSelfdripcoffee.getRegistered_time());
        newSelfdripcoffee.setSelfdripcoffee_name(_binding.nameSelfdripcoffeeEdit.getText().toString());
        newSelfdripcoffee.setSelfdripcoffee_drink_date(_binding.drinkDateSelfdripcoffeeEdit.getText().toString());
        newSelfdripcoffee.setSelfdripcoffee_drink_place(_binding.drinkPlaceSelfdripcoffeeEdit.getText().toString());

        if(_binding.drinkAmountSelfdripcoffeeEdit.getText().toString().isEmpty())
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(0);
        else if(_vm.isNumeric(_binding.drinkAmountSelfdripcoffeeEdit.getText().toString()))
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(Integer.valueOf(_binding.drinkAmountSelfdripcoffeeEdit.getText().toString()));
        else
            newSelfdripcoffee.setSelfdripcoffee_drink_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceSelfdripcoffeeEdit.getText().toString().isEmpty())
            newSelfdripcoffee.setSelfdripcoffee_price(0);
        else if(_vm.isNumeric(_binding.priceSelfdripcoffeeEdit.getText().toString()))
            newSelfdripcoffee.setSelfdripcoffee_price(Integer.valueOf(_binding.priceSelfdripcoffeeEdit.getText().toString()));
        else
            newSelfdripcoffee.setSelfdripcoffee_price(_vm.WRONG_PRICE);

        if(_binding.roastbeansSpinnerSelfdripcoffeeEdit.getSelectedItemPosition() >= 0){
            newSelfdripcoffee.setSelfdripcoffee_roastbeans(
                    _vm.positionToId(
                            _binding.roastbeansSpinnerSelfdripcoffeeEdit.getSelectedItemPosition()
                    )
            );
        }

        newSelfdripcoffee.setSelfdripcoffee_dripmethod(_binding.dripmethodSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_drink_method_temperature(_binding.drinkMethodTemperatureSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_drink_method_type(_binding.drinkMethodTypeSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());

        newSelfdripcoffee.setSelfdripcoffee_taste_sweet(_binding.tasteSweetSeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_bitter(_binding.tasteBitterSeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_sour(_binding.tasteSourSeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_scent(_binding.tasteScentSeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_body(_binding.tasteBodySeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_taste_rich(_binding.tasteRichSeekBarSelfdripcoffeeEdit.getProgress());

        newSelfdripcoffee.setSelfdripcoffee_flavor(_binding.flavorSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_match_food(_binding.matchFoodSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());
        newSelfdripcoffee.setSelfdripcoffee_atmosphere(_binding.atmosphereSpinnerSelfdripcoffeeEdit.getSelectedItemPosition());

        newSelfdripcoffee.setSelfdripcoffee_caffeineless(_binding.caffeinelessCheckboxSelfdripcoffeeEdit.isChecked());
        newSelfdripcoffee.setSelfdripcoffee_review(_binding.reviewSeekBarSelfdripcoffeeEdit.getProgress());
        newSelfdripcoffee.setSelfdripcoffee_memo(_binding.memoSelfdripcoffeeEdit.getText().toString());

        return newSelfdripcoffee;
    }
}
