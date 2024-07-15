package ymz.coffeerep.scenes.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ymz.coffeerep.R;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.databinding.FragmentRawbeansDetailBinding;
import ymz.coffeerep.scenes.list.BeansListFragmentDirections;

public class RawBeansDetailFragment extends Fragment {

    private FragmentRawbeansDetailBinding _binding;
    private RawBeansDetailViewModel _vm;
    private RawBeans _selectedRawbeans;

    //constructor
    public RawBeansDetailFragment() {
        super(R.layout.fragment_rawbeans_detail);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansDetailBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansDetailViewModel.class);

        //get specific item by safe-args
        _selectedRawbeans = RawBeansDetailFragmentArgs.fromBundle(getArguments()).getSpecRawbeans();
        showDetail(_selectedRawbeans);
        Log.d("YMZdebug", "[RawBeansDetailFragment.onViewCreated]: ID is " + _selectedRawbeans.getRawbeans_id());

        //edit button listener
        _binding.editButtonRawbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jump to editFragment with specific item
                RawBeansDetailFragmentDirections.ActionRawBeansDetailFragmentToRawBeansEditFragment
                        action = RawBeansDetailFragmentDirections.actionRawBeansDetailFragmentToRawBeansEditFragment(_selectedRawbeans);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //update view & specific item when complete update in editFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_update_rawbeans", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                _selectedRawbeans = (RawBeans) bundle.getSerializable("bundleKey_update_rawbeans");
                showDetail(_selectedRawbeans);
            }
        });

        //delete button listener
        _binding.deleteButtonRawbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_rawBeansDetailFragment_to_confirmDeleteFragment);
            }
        });

        //delete or do-nothing when button tapped in dialogFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_confirm_delete", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                int which = bundle.getInt("bundleKey_confirm_delete");
                if(which == DialogInterface.BUTTON_POSITIVE){
                    _vm.delete(_selectedRawbeans);
                }
                //else: auto-close dialogFragment
            }
        });

        //error message in delete
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to list-fragment when complete delete
        _vm.complete.observe(getViewLifecycleOwner(), complete -> {
            if(complete){
                //avoid from close only dialogFragment, specify go-back-destination fragment
                Navigation.findNavController(view).popBackStack(R.id.beansListFragment, false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void showDetail(RawBeans rawbeans){
        _binding.nameRawbeansDetail.setText(rawbeans.getRawbeans_name());
        _binding.registeredDateRawbeansDetail.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", rawbeans.getRegistered_time())
        );
        _binding.purchasedDateRawbeansDetail.setText(rawbeans.getRawbeans_purchased_date());
        _binding.purchasedShopRawbeansDetail.setText(rawbeans.getRawbeans_purchased_shop());
        _binding.amountRawbeansDetail.setText(Integer.toString(rawbeans.getRawbeans_amount()));
        _binding.countryRawbeansDatail.setText(rawbeans.getRawbeans_country());
        _binding.placeRawbeansDatail.setText(rawbeans.getRawbeans_place());
        _binding.farmRawbeansDatail.setText(rawbeans.getRawbeans_farm());
        _binding.varietyRawbeansDatail.setText(rawbeans.getRawbeans_variety());
        _binding.processRawbeansDatail.setText(rawbeans.getRawbeans_process());
        _binding.caffeinelessCheckboxRawbeansDatail.setChecked(rawbeans.getRawbeans_caffeineless());
        _binding.reviewRatingbarRawbeansDatail.setRating(rawbeans.getRawbeans_review());
        _binding.memoRawbeansDatail.setText(rawbeans.getRawbeans_memo());
    }
}