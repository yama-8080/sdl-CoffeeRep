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
import ymz.coffeerep.data.dropdown.DrinkMethodTemperatureItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTypeItem;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.databinding.FragmentReadymadecoffeeEditBinding;

public class ReadyMadeCoffeeEditFragment extends Fragment {

    private FragmentReadymadecoffeeEditBinding _binding;
    private ReadyMadeCoffeeEditViewModel _vm;

    //constructor
    public ReadyMadeCoffeeEditFragment() {
        super(R.layout.fragment_readymadecoffee_edit);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentReadymadecoffeeEditBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentReadymadecoffeeEditBinding.bind(view);

        _vm = new ViewModelProvider(this).get(ReadyMadeCoffeeEditViewModel.class);

        spinnerConfig();

        //get specific item by safe-args
        ReadyMadeCoffee selectedReadymadecoffee = ReadyMadeCoffeeEditFragmentArgs.fromBundle(getArguments()).getSpecReadymadecoffee();
        showDefaultDetail(selectedReadymadecoffee);
        Log.d("YMZdebug", "[ReadyMadeCoffeeEditFragment.onViewCreated]: ID is " + selectedReadymadecoffee.getReadymadecoffee_id());

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to detail-fragment when complete update
        _vm.complete.observe(getViewLifecycleOwner(), updatedReadymadecoffee -> {
            if(updatedReadymadecoffee != null){
                Bundle result = new Bundle();
                result.putSerializable("bundleKey_update_readymadecoffee", updatedReadymadecoffee);
                getParentFragmentManager().setFragmentResult("requestKey_update_readymadecoffee", result);
                Navigation.findNavController(view).popBackStack();
            }
        });

        //update
        _binding.updateFabReadymadecoffeeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update(selectedReadymadecoffee);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void showDefaultDetail(ReadyMadeCoffee readymadecoffee){
        _binding.nameReadymadecoffeeEdit.setText(readymadecoffee.getReadymadecoffee_name());
        _binding.drinkDateReadymadecoffeeEdit.setText(readymadecoffee.getReadymadecoffee_drink_date());
        _binding.drinkPlaceReadymadecoffeeEdit.setText(readymadecoffee.getReadymadecoffee_drink_place());
        _binding.drinkAmountReadymadecoffeeEdit.setText(Integer.toString(readymadecoffee.getReadymadecoffee_drink_amount()));
        _binding.priceReadymadecoffeeEdit.setText(Integer.toString(readymadecoffee.getReadymadecoffee_price()));

        _binding.drinkMethodTemperatureSpinnerReadymadecoffeeEdit.setSelection(readymadecoffee.getReadymadecoffee_drink_method_temperature());
        _binding.drinkMethodTypeSpinnerReadymadecoffeeEdit.setSelection(readymadecoffee.getReadymadecoffee_drink_method_type());

        _binding.tasteSweetSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_sweet());
        _binding.tasteBitterSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_bitter());
        _binding.tasteSourSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_sour());
        _binding.tasteScentSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_scent());
        _binding.tasteBodySeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_body());
        _binding.tasteRichSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_taste_rich());

        _binding.flavorSpinnerReadymadecoffeeEdit.setSelection(readymadecoffee.getReadymadecoffee_flavor());
        _binding.matchFoodSpinnerReadymadecoffeeEdit.setSelection(readymadecoffee.getReadymadecoffee_match_food());
        _binding.atmosphereSpinnerReadymadecoffeeEdit.setSelection(readymadecoffee.getReadymadecoffee_atmosphere());

        _binding.caffeinelessCheckboxReadymadecoffeeEdit.setChecked(readymadecoffee.getReadymadecoffee_caffeineless());
        _binding.reviewSeekBarReadymadecoffeeEdit.setProgress(readymadecoffee.getReadymadecoffee_review());
        _binding.memoReadymadecoffeeEdit.setText(readymadecoffee.getReadymadecoffee_memo());
    }

    //configure spinner
    private void spinnerConfig(){
        //for drink_method_temperature
        ArrayAdapter<String> drinkMethodTempAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTemperatureItem.setItemsToAdapter(drinkMethodTempAdapter);
        _binding.drinkMethodTemperatureSpinnerReadymadecoffeeEdit.setAdapter(drinkMethodTempAdapter);

        //for drink_method_type
        ArrayAdapter<String> drinkMethodTypeAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        drinkMethodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DrinkMethodTypeItem.setItemsToAdapter(drinkMethodTypeAdapter);
        _binding.drinkMethodTypeSpinnerReadymadecoffeeEdit.setAdapter(drinkMethodTypeAdapter);

        //for flavor
        ArrayAdapter<String> flavorAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FlavorItem.setItemsToAdapter(flavorAdapter);
        _binding.flavorSpinnerReadymadecoffeeEdit.setAdapter(flavorAdapter);

        //for match_food
        ArrayAdapter<String> matchFoodAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        matchFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MatchFoodItem.setItemsToAdapter(matchFoodAdapter);
        _binding.matchFoodSpinnerReadymadecoffeeEdit.setAdapter(matchFoodAdapter);

        //for atmosphere
        ArrayAdapter<String> atmosphereAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        atmosphereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AtmosphereItem.setItemsToAdapter(atmosphereAdapter);
        _binding.atmosphereSpinnerReadymadecoffeeEdit.setAdapter(atmosphereAdapter);
    }

    private void update(ReadyMadeCoffee oldReadymadecoffee) {
        _vm.update(createNewReadyMadeCoffee(oldReadymadecoffee));
    }

    //create new entity
    private ReadyMadeCoffee createNewReadyMadeCoffee(ReadyMadeCoffee oldReadymadecoffee){
        ReadyMadeCoffee newReadymadecoffee = new ReadyMadeCoffee();

        newReadymadecoffee.setReadymadecoffee_id(oldReadymadecoffee.getReadymadecoffee_id());
        newReadymadecoffee.setRegistered_time(oldReadymadecoffee.getRegistered_time());
        newReadymadecoffee.setReadymadecoffee_name(_binding.nameReadymadecoffeeEdit.getText().toString());
        newReadymadecoffee.setReadymadecoffee_drink_date(_binding.drinkDateReadymadecoffeeEdit.getText().toString());
        newReadymadecoffee.setReadymadecoffee_drink_place(_binding.drinkPlaceReadymadecoffeeEdit.getText().toString());

        if(_binding.drinkAmountReadymadecoffeeEdit.getText().toString().isEmpty())
            newReadymadecoffee.setReadymadecoffee_drink_amount(0);
        else if(_vm.isNumeric(_binding.drinkAmountReadymadecoffeeEdit.getText().toString()))
            newReadymadecoffee.setReadymadecoffee_drink_amount(Integer.valueOf(_binding.drinkAmountReadymadecoffeeEdit.getText().toString()));
        else
            newReadymadecoffee.setReadymadecoffee_drink_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceReadymadecoffeeEdit.getText().toString().isEmpty())
            newReadymadecoffee.setReadymadecoffee_price(0);
        else if(_vm.isNumeric(_binding.priceReadymadecoffeeEdit.getText().toString()))
            newReadymadecoffee.setReadymadecoffee_price(Integer.valueOf(_binding.priceReadymadecoffeeEdit.getText().toString()));
        else
            newReadymadecoffee.setReadymadecoffee_price(_vm.WRONG_PRICE);

        newReadymadecoffee.setReadymadecoffee_drink_method_temperature(_binding.drinkMethodTemperatureSpinnerReadymadecoffeeEdit.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_drink_method_type(_binding.drinkMethodTypeSpinnerReadymadecoffeeEdit.getSelectedItemPosition());

        newReadymadecoffee.setReadymadecoffee_taste_sweet(_binding.tasteSweetSeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_bitter(_binding.tasteBitterSeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_sour(_binding.tasteSourSeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_scent(_binding.tasteScentSeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_body(_binding.tasteBodySeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_taste_rich(_binding.tasteRichSeekBarReadymadecoffeeEdit.getProgress());

        newReadymadecoffee.setReadymadecoffee_flavor(_binding.flavorSpinnerReadymadecoffeeEdit.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_match_food(_binding.matchFoodSpinnerReadymadecoffeeEdit.getSelectedItemPosition());
        newReadymadecoffee.setReadymadecoffee_atmosphere(_binding.atmosphereSpinnerReadymadecoffeeEdit.getSelectedItemPosition());

        newReadymadecoffee.setReadymadecoffee_caffeineless(_binding.caffeinelessCheckboxReadymadecoffeeEdit.isChecked());
        newReadymadecoffee.setReadymadecoffee_review(_binding.reviewSeekBarReadymadecoffeeEdit.getProgress());
        newReadymadecoffee.setReadymadecoffee_memo(_binding.memoReadymadecoffeeEdit.getText().toString());

        return newReadymadecoffee;
    }
}
