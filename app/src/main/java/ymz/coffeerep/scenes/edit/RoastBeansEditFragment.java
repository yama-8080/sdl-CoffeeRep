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
import ymz.coffeerep.data.dropdown.BrendItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.RoastLevelItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.databinding.FragmentRoastbeansEditBinding;

public class RoastBeansEditFragment extends Fragment {

    private FragmentRoastbeansEditBinding _binding;
    private RoastBeansEditViewModel _vm;

    //constructor
    public RoastBeansEditFragment() {
        super(R.layout.fragment_roastbeans_edit);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRoastbeansEditBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRoastbeansEditBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RoastBeansEditViewModel.class);

        spinnerConfig();

        //get specific item by safe-args
        RoastBeans selectedRoastbeans = RoastBeansEditFragmentArgs.fromBundle(getArguments()).getSpecRoastbeans();
        showDefaultDetail(selectedRoastbeans);
        Log.d("YMZdebug", "[RoastBeansEditFragment.onViewCreated]: ID is " + selectedRoastbeans.getRoastbeans_id());

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to detail-fragment when complete update
        _vm.complete.observe(getViewLifecycleOwner(), updatedRoastbeans -> {
            if(updatedRoastbeans != null){
                Bundle result = new Bundle();
                result.putSerializable("bundleKey_update_roastbeans", updatedRoastbeans);
                getParentFragmentManager().setFragmentResult("requestKey_update_roastbeans", result);
                Navigation.findNavController(view).popBackStack();
            }
        });

        //update
        _binding.updateFabRoastbeansEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update(selectedRoastbeans);
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
        _binding.brendSpinnerRoastbeansEdit.setAdapter(brendAdapter);

        //for roastlevel
        ArrayAdapter<String> roastlevelAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        roastlevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RoastLevelItem.setItemsToAdapter(roastlevelAdapter);
        _binding.roastLevelSpinnerRoastbeansEdit.setAdapter(roastlevelAdapter);

        //for roast_rawbeans
        ArrayAdapter<String> roastRawbeansAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        roastRawbeansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _vm.getAllRawBeans().observe(getViewLifecycleOwner(), rawbeans -> {
            //LiveData does not have a value unless it has an observer
            _vm.setRoastRawbeansItemsToAdapter(roastRawbeansAdapter, rawbeans);
        });
        _binding.roastRawbeansSpinnerRoastbeansEdit.setAdapter(roastRawbeansAdapter);

        //for variety
        ArrayAdapter<String> varietyAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VarietyItem.setItemsToAdapter(varietyAdapter);
        _binding.varietySpinnerRoastbeansEdit.setAdapter(varietyAdapter);

        //for process
        ArrayAdapter<String> processAdapter = new ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item);
        processAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProcessItem.setItemsToAdapter(processAdapter);
        _binding.processSpinnerRoastbeansEdit.setAdapter(processAdapter);
    }

    private void showDefaultDetail(RoastBeans roastbeans){
        _binding.nameRoastbeansEdit.setText(roastbeans.getRoastbeans_name());
        _binding.countryRoastbeansEdit.setText(roastbeans.getRoastbeans_country());

        _binding.nameRoastbeansEdit.setText(roastbeans.getRoastbeans_name());
        _binding.purchasedDateRoastbeansEdit.setText(roastbeans.getRoastbeans_purchased_date());
        _binding.purchasedShopRoastbeansEdit.setText(roastbeans.getRoastbeans_purchased_shop());
        _binding.amountRoastbeansEdit.setText(Integer.toString(roastbeans.getRoastbeans_amount()));
        _binding.brendSpinnerRoastbeansEdit.setSelection(roastbeans.getRoastbeans_brend());
        _binding.selfRoastCheckboxRoastbeansEdit.setChecked(roastbeans.getRoastbeans_self_roast());

        //avoid from executed this before vm make position-rawbeans_id array
        _vm.makeOutArray.observe(getViewLifecycleOwner(), makeOutArray -> {
            if(makeOutArray){
                //null checking in case rawbeans deleted
                if(_vm.idToPosition(roastbeans.getRoastbeans_roast_rawbeans_id()) != _vm.ID_NOT_FOUND){
                    _binding.roastRawbeansSpinnerRoastbeansEdit.setSelection(
                            _vm.idToPosition(
                                    roastbeans.getRoastbeans_roast_rawbeans_id()
                            )
                    );
                }
            }
        });

        _binding.roastLevelSpinnerRoastbeansEdit.setSelection(roastbeans.getRoastbeans_roast_level());
        _binding.countryRoastbeansEdit.setText(roastbeans.getRoastbeans_country());
        _binding.placeRoastbeansEdit.setText(roastbeans.getRoastbeans_place());
        _binding.farmRoastbeansEdit.setText(roastbeans.getRoastbeans_farm());
        _binding.varietySpinnerRoastbeansEdit.setSelection(roastbeans.getRoastbeans_variety());
        _binding.processSpinnerRoastbeansEdit.setSelection(roastbeans.getRoastbeans_process());
        _binding.caffeinelessCheckboxRoastbeansEdit.setChecked(roastbeans.getRoastbeans_caffeineless());
        _binding.reviewSeekBarRoastbeansEdit.setProgress(roastbeans.getRoastbeans_review());
        _binding.memoRoastbeansEdit.setText(roastbeans.getRoastbeans_memo());
    }

    private void update(RoastBeans oldRoastbeans) {
        _vm.update(createNewRoastBeans(oldRoastbeans));
    }

    //create new entity
    private RoastBeans createNewRoastBeans(RoastBeans oldRoastbeans){
        RoastBeans newRoastbeans = new RoastBeans();

        newRoastbeans.setRoastbeans_id(oldRoastbeans.getRoastbeans_id());
        newRoastbeans.setRegistered_time(oldRoastbeans.getRegistered_time());
        newRoastbeans.setRoastbeans_name(_binding.nameRoastbeansEdit.getText().toString());
        newRoastbeans.setRoastbeans_purchased_date(_binding.purchasedDateRoastbeansEdit.getText().toString());
        newRoastbeans.setRoastbeans_purchased_shop(_binding.purchasedShopRoastbeansEdit.getText().toString());

        if(_binding.amountRoastbeansEdit.getText().toString().isEmpty())
            newRoastbeans.setRoastbeans_amount(0);
        else if(_vm.isNumeric(_binding.amountRoastbeansEdit.getText().toString()))
            newRoastbeans.setRoastbeans_amount(Integer.valueOf(_binding.amountRoastbeansEdit.getText().toString()));
        else
            newRoastbeans.setRoastbeans_amount(_vm.WRONG_AMOUNT);

        newRoastbeans.setRoastbeans_brend(_binding.brendSpinnerRoastbeansEdit.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_self_roast(_binding.selfRoastCheckboxRoastbeansEdit.isChecked());

        //null checking in case rawbeans deleted
        if(_binding.roastRawbeansSpinnerRoastbeansEdit.getSelectedItemPosition() >= 0){
            newRoastbeans.setRoastbeans_roast_rawbeans_id(
                    _vm.positionToId(
                            _binding.roastRawbeansSpinnerRoastbeansEdit.getSelectedItemPosition()
                    )
            );
        }

        newRoastbeans.setRoastbeans_roast_level(_binding.roastLevelSpinnerRoastbeansEdit.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_country(_binding.countryRoastbeansEdit.getText().toString());
        newRoastbeans.setRoastbeans_place(_binding.placeRoastbeansEdit.getText().toString());
        newRoastbeans.setRoastbeans_farm(_binding.farmRoastbeansEdit.getText().toString());
        newRoastbeans.setRoastbeans_variety(_binding.varietySpinnerRoastbeansEdit.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_process(_binding.processSpinnerRoastbeansEdit.getSelectedItemPosition());
        newRoastbeans.setRoastbeans_caffeineless(_binding.caffeinelessCheckboxRoastbeansEdit.isChecked());
        newRoastbeans.setRoastbeans_review(_binding.reviewSeekBarRoastbeansEdit.getProgress());
        newRoastbeans.setRoastbeans_memo(_binding.memoRoastbeansEdit.getText().toString());

        return newRoastbeans;
    }
}
