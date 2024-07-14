package ymz.coffeerep.scenes.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ymz.coffeerep.R;
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
        _binding.countryRawbeansEdit.setText(rawbeans.getRawbeans_country());
    }

    private void update(RawBeans oldRawbeans) {
        _vm.update(createNewRawBeans(oldRawbeans));
    }

    //create new entity
    private RawBeans createNewRawBeans(RawBeans oldRawbeans){
        RawBeans newRawbeans = new RawBeans();

        newRawbeans.setRawbeans_id(oldRawbeans.getRawbeans_id());
        newRawbeans.setRawbeans_name(_binding.nameRawbeansEdit.getText().toString());
        newRawbeans.setRawbeans_country(_binding.countryRawbeansEdit.getText().toString());
        newRawbeans.setRegistered_time(oldRawbeans.getRegistered_time());   //should be editable

        return newRawbeans;
    }
}
