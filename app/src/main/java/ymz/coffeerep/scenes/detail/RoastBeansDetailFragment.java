package ymz.coffeerep.scenes.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ymz.coffeerep.R;
import ymz.coffeerep.data.dropdown.BrendItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.RoastLevelItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.databinding.FragmentRoastbeansDetailBinding;

public class RoastBeansDetailFragment extends Fragment {

    private FragmentRoastbeansDetailBinding _binding;
    private RoastBeansDetailViewModel _vm;
    private RoastBeans _selectedRoastbeans;

    //constructor
    public RoastBeansDetailFragment() {
        super(R.layout.fragment_roastbeans_detail);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRoastbeansDetailBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRoastbeansDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RoastBeansDetailViewModel.class);

        //get specific item by safe-args
        _selectedRoastbeans = RoastBeansDetailFragmentArgs.fromBundle(getArguments()).getSpecRoastbeans();
        showDetail(_selectedRoastbeans);
        Log.d("YMZdebug", "[RoastBeansDetailFragment.onViewCreated]: ID is " + _selectedRoastbeans.getRoastbeans_id());

        //edit button listener
        _binding.editButtonRoastbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jump to editFragment with specific item
                RoastBeansDetailFragmentDirections.ActionRoastBeansDetailFragmentToRoastBeansEditFragment
                        action = RoastBeansDetailFragmentDirections.actionRoastBeansDetailFragmentToRoastBeansEditFragment(_selectedRoastbeans);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //update view & specific item when complete update in editFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_update_roastbeans", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                _selectedRoastbeans = (RoastBeans) bundle.getSerializable("bundleKey_update_roastbeans");
                showDetail(_selectedRoastbeans);
            }
        });

        //delete button listener
        _binding.deleteButtonRoastbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_roastBeansDetailFragment_to_confirmDeleteFragment);
            }
        });

        //delete or do-nothing when button tapped in dialogFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_confirm_delete", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                int which = bundle.getInt("bundleKey_confirm_delete");
                if(which == DialogInterface.BUTTON_POSITIVE){
                    _vm.delete(_selectedRoastbeans);
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

    private void showDetail(RoastBeans roastbeans){
        _binding.nameRoastbeansDetail.setText(roastbeans.getRoastbeans_name());
        _binding.registeredDateRoastbeansDetail.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", roastbeans.getRegistered_time())
        );
        _binding.purchasedDateRoastbeansDetail.setText(roastbeans.getRoastbeans_purchased_date());
        _binding.purchasedShopRoastbeansDetail.setText(roastbeans.getRoastbeans_purchased_shop());
        _binding.amountRoastbeansDetail.setText(Integer.toString(roastbeans.getRoastbeans_amount()));
        _binding.brendRoastbeansDatail.setText(
                BrendItem.Brend.getType(
                        roastbeans.getRoastbeans_brend()
                ).getString()
        );
        _binding.selfRoastCheckboxRoastbeansDatail.setChecked(roastbeans.getRoastbeans_self_roast());
        _vm.getRawBeans(roastbeans.getRoastbeans_roast_rawbeans_id())
                .observe(getViewLifecycleOwner(), rawbeans -> {
            //LiveData does not have a value unless it has an observer
            _binding.roastRawbeansRoastbeansDatail.setText(
                    rawbeans.getRawbeans_name()
            );
        });
        _binding.roastLevelRoastbeansDatail.setText(
                RoastLevelItem.RoastLevel.getType(
                        roastbeans.getRoastbeans_roast_level()
                ).getString()
        );
        _binding.countryRoastbeansDatail.setText(roastbeans.getRoastbeans_country());
        _binding.placeRoastbeansDatail.setText(roastbeans.getRoastbeans_place());
        _binding.farmRoastbeansDatail.setText(roastbeans.getRoastbeans_farm());
        _binding.varietyRoastbeansDatail.setText(
                VarietyItem.Variety.getType(
                        roastbeans.getRoastbeans_variety()
                ).getString()
        );
        _binding.processRoastbeansDatail.setText(
                ProcessItem.Process.getType(
                        roastbeans.getRoastbeans_process()
                ).getString()
        );
        _binding.caffeinelessCheckboxRoastbeansDatail.setChecked(roastbeans.getRoastbeans_caffeineless());
        _binding.reviewRatingbarRoastbeansDatail.setRating(roastbeans.getRoastbeans_review());
        _binding.memoRoastbeansDatail.setText(roastbeans.getRoastbeans_memo());
    }
}
