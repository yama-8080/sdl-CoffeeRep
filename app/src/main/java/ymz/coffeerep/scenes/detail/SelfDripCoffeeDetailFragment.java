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
import ymz.coffeerep.data.dropdown.AtmosphereItem;
import ymz.coffeerep.data.dropdown.BrendItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTemperatureItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTypeItem;
import ymz.coffeerep.data.dropdown.DropDownDefault;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.dropdown.ProcessItem;
import ymz.coffeerep.data.dropdown.RoastLevelItem;
import ymz.coffeerep.data.dropdown.VarietyItem;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.databinding.FragmentSelfdripcoffeeDetailBinding;

public class SelfDripCoffeeDetailFragment  extends Fragment {

    private FragmentSelfdripcoffeeDetailBinding _binding;
    private SelfDripCoffeeDetailViewModel _vm;
    private SelfDripCoffee _selectedSelfdripcoffee;

    //constructor
    public SelfDripCoffeeDetailFragment() {
        super(R.layout.fragment_selfdripcoffee_detail);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentSelfdripcoffeeDetailBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentSelfdripcoffeeDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(SelfDripCoffeeDetailViewModel.class);

        //get specific item by safe-args
        _selectedSelfdripcoffee = SelfDripCoffeeDetailFragmentArgs.fromBundle(getArguments()).getSpecSelfdripcoffee();
        showDetail(_selectedSelfdripcoffee);
        Log.d("YMZdebug", "[SelfDripCoffeeDetailFragment.onViewCreated]: ID is " + _selectedSelfdripcoffee.getSelfdripcoffee_id());

        //edit button listener
        _binding.editButtonSelfdripcoffeeDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jump to editFragment with specific item
                SelfDripCoffeeDetailFragmentDirections.ActionSelfDripCoffeeDetailFragmentToSelfDripCoffeeEditFragment
                        action = SelfDripCoffeeDetailFragmentDirections.actionSelfDripCoffeeDetailFragmentToSelfDripCoffeeEditFragment(_selectedSelfdripcoffee);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //update view & specific item when complete update in editFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_update_selfdripcoffee", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                _selectedSelfdripcoffee = (SelfDripCoffee) bundle.getSerializable("bundleKey_update_selfdripcoffee");
                showDetail(_selectedSelfdripcoffee);
            }
        });

        //delete button listener
        _binding.deleteButtonSelfdripcoffeeDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_selfDripCoffeeDetailFragment_to_confirmDeleteFragment);
            }
        });

        //delete or do-nothing when button tapped in dialogFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_confirm_delete", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                int which = bundle.getInt("bundleKey_confirm_delete");
                if(which == DialogInterface.BUTTON_POSITIVE){
                    _vm.delete(_selectedSelfdripcoffee);
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

    private void showDetail(SelfDripCoffee selfdripcoffee){
        _binding.nameSelfdripcoffeeDetail.setText(selfdripcoffee.getSelfdripcoffee_name());
        _binding.registeredDateSelfdripcoffeeDetail.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", selfdripcoffee.getRegistered_time())
        );
        _binding.drinkDateSelfdripcoffeeDetail.setText(selfdripcoffee.getSelfdripcoffee_drink_date());
        _binding.drinkPlaceSelfdripcoffeeDetail.setText(selfdripcoffee.getSelfdripcoffee_drink_place());
        _binding.drinkAmountSelfdripcoffeeDetail.setText(Integer.toString(selfdripcoffee.getSelfdripcoffee_drink_amount()));
        _binding.priceSelfdripcoffeeDetail.setText(Integer.toString(selfdripcoffee.getSelfdripcoffee_price()));

        //null checking in case roastbeans deleted
        if(selfdripcoffee.getSelfdripcoffee_roastbeans() == DropDownDefault.getDefault_id()){
            _binding.roastbeansSelfdripcoffeeDetail.setText(
                    DropDownDefault.getDefault_str()
            );
        }
        else{
            //LiveData does not have a value unless it has an observer
            _vm.getRoastBeans(selfdripcoffee.getSelfdripcoffee_roastbeans())
                    .observe(getViewLifecycleOwner(), roastbeans -> {
                        _binding.roastbeansSelfdripcoffeeDetail.setText(
                                roastbeans.getRoastbeans_name()
                        );
                    });
        }

        _binding.drinkMethodTemperatureSelfdripcoffeeDatail.setText(
                DrinkMethodTemperatureItem.DrinkMethodTemperature.getType(
                        selfdripcoffee.getSelfdripcoffee_drink_method_temperature()
                ).getString()
        );
        _binding.drinkMethodTemperatureSelfdripcoffeeDatail.setText(
                DrinkMethodTemperatureItem.DrinkMethodTemperature.getType(
                        selfdripcoffee.getSelfdripcoffee_drink_method_temperature()
                ).getString()
        );
        _binding.drinkMethodTypeSelfdripcoffeeDatail.setText(
                DrinkMethodTypeItem.DrinkMethodType.getType(
                        selfdripcoffee.getSelfdripcoffee_drink_method_type()
                ).getString()
        );
        _binding.tasteSweetRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_sweet());
        _binding.tasteBitterRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_bitter());
        _binding.tasteSourRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_sour());
        _binding.tasteScentRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_scent());
        _binding.tasteBodyRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_body());
        _binding.tasteRichRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_taste_rich());
        _binding.flavorSelfdripcoffeeDatail.setText(
                FlavorItem.Flavor.getType(
                        selfdripcoffee.getSelfdripcoffee_flavor()
                ).getString()
        );
        _binding.matchFoodSelfdripcoffeeDatail.setText(
                MatchFoodItem.MatchFood.getType(
                        selfdripcoffee.getSelfdripcoffee_match_food()
                ).getString()
        );
        _binding.atmosphereSelfdripcoffeeDatail.setText(
                AtmosphereItem.Atmosphere.getType(
                        selfdripcoffee.getSelfdripcoffee_atmosphere()
                ).getString()
        );
        _binding.caffeinelessCheckboxSelfdripcoffeeDatail.setChecked(selfdripcoffee.getSelfdripcoffee_caffeineless());
        _binding.reviewRatingbarSelfdripcoffeeDatail.setRating(selfdripcoffee.getSelfdripcoffee_review());
        _binding.memoSelfdripcoffeeDatail.setText(selfdripcoffee.getSelfdripcoffee_memo());
    }
}
