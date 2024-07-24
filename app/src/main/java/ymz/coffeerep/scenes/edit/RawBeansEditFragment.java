package ymz.coffeerep.scenes.edit;

import android.os.Bundle;
import android.text.format.DateFormat;
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
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.databinding.FragmentRawbeansEditBinding;

public class RawBeansEditFragment extends Fragment {

    private FragmentRawbeansEditBinding _binding;
    private RawBeansEditViewModel _vm;

    //constructor
    public RawBeansEditFragment() {
        super(R.layout.fragment_rawbeans_edit);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansEditBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansEditBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansEditViewModel.class);

        spinnerConfig();

        //get specific item by safe-args
        RawBeans selectedRawbeans = RawBeansEditFragmentArgs.fromBundle(getArguments()).getSpecRawbeans();
        showDefaultDetail(selectedRawbeans);
        Log.d("YMZdebug", "[RawBeansEditFragment.onViewCreated]: ID is " + selectedRawbeans.getRawbeans_id());

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to detail-fragment when complete update
        _vm.complete.observe(getViewLifecycleOwner(), updatedRawbeans -> {
            if(updatedRawbeans != null){
                Bundle result = new Bundle();
                result.putSerializable("bundleKey_update_rawbeans", updatedRawbeans);
                getParentFragmentManager().setFragmentResult("requestKey_update_rawbeans", result);
                Navigation.findNavController(view).popBackStack();
            }
        });

        //update
        _binding.updateFabRawbeansEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update(selectedRawbeans);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void showDefaultDetail(RawBeans rawbeans){
        _binding.nameRawbeansEdit.setText(rawbeans.getRawbeans_name());
        _binding.purchasedDateRawbeansEdit.setText(rawbeans.getRawbeans_purchased_date());
        _binding.purchasedShopRawbeansEdit.setText(rawbeans.getRawbeans_purchased_shop());
        _binding.amountRawbeansEdit.setText(Integer.toString(rawbeans.getRawbeans_amount()));
        _binding.priceRawbeansEdit.setText(Integer.toString(rawbeans.getRawbeans_price()));
        _binding.countryRawbeansEdit.setText(rawbeans.getRawbeans_country());
        _binding.placeRawbeansEdit.setText(rawbeans.getRawbeans_place());
        _binding.farmRawbeansEdit.setText(rawbeans.getRawbeans_farm());
        _binding.varietySpinnerRawbeansEdit.setSelection(rawbeans.getRawbeans_variety());
        _binding.processSpinnerRawbeansEdit.setSelection(rawbeans.getRawbeans_process());
        _binding.caffeinelessCheckboxRawbeansEdit.setChecked(rawbeans.getRawbeans_caffeineless());
        _binding.reviewSeekBarRawbeansEdit.setProgress(rawbeans.getRawbeans_review());
        _binding.memoRawbeansEdit.setText(rawbeans.getRawbeans_memo());
    }

    //configure spinner
    private void spinnerConfig(){
        //for variety
        ArrayAdapter<String> varietyAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VarietyItem.setItemsToAdapter(varietyAdapter);
        _binding.varietySpinnerRawbeansEdit.setAdapter(varietyAdapter);

        //for process
        ArrayAdapter<String> processAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        processAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProcessItem.setItemsToAdapter(processAdapter);
        _binding.processSpinnerRawbeansEdit.setAdapter(processAdapter);
    }

    private void update(RawBeans oldRawbeans) {
        _vm.update(createNewRawBeans(oldRawbeans));
    }

    //create new entity
    private RawBeans createNewRawBeans(RawBeans oldRawbeans){
        RawBeans newRawbeans = new RawBeans();

        newRawbeans.setRawbeans_id(oldRawbeans.getRawbeans_id());
        newRawbeans.setRegistered_time(oldRawbeans.getRegistered_time());
        newRawbeans.setRawbeans_name(_binding.nameRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_purchased_date(_binding.purchasedDateRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_purchased_shop(_binding.purchasedShopRawbeansEdit.getText().toString());

        if(_binding.amountRawbeansEdit.getText().toString().isEmpty())
            newRawbeans.setRawbeans_amount(0);
        else if(_vm.isNumeric(_binding.amountRawbeansEdit.getText().toString()))
            newRawbeans.setRawbeans_amount(Integer.valueOf(_binding.amountRawbeansEdit.getText().toString()));
        else
            newRawbeans.setRawbeans_amount(_vm.WRONG_AMOUNT);

        if(_binding.priceRawbeansEdit.getText().toString().isEmpty())
            newRawbeans.setRawbeans_price(0);
        else if(_vm.isNumeric(_binding.priceRawbeansEdit.getText().toString()))
            newRawbeans.setRawbeans_price(Integer.valueOf(_binding.priceRawbeansEdit.getText().toString()));
        else
            newRawbeans.setRawbeans_price(_vm.WRONG_PRICE);

        newRawbeans.setRawbeans_country(_binding.countryRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_place(_binding.placeRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_farm(_binding.farmRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_variety(_binding.varietySpinnerRawbeansEdit.getSelectedItemPosition());
        newRawbeans.setRawbeans_process(_binding.processSpinnerRawbeansEdit.getSelectedItemPosition());
        newRawbeans.setRawbeans_caffeineless(_binding.caffeinelessCheckboxRawbeansEdit.isChecked());
        newRawbeans.setRawbeans_review(_binding.reviewSeekBarRawbeansEdit.getProgress());
        newRawbeans.setRawbeans_memo(_binding.memoRawbeansEdit.getText().toString());

        return newRawbeans;
    }
}
