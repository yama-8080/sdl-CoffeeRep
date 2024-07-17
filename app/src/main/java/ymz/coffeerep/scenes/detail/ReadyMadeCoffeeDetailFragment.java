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
import ymz.coffeerep.data.dropdown.DrinkMethodTemperatureItem;
import ymz.coffeerep.data.dropdown.DrinkMethodTypeItem;
import ymz.coffeerep.data.dropdown.FlavorItem;
import ymz.coffeerep.data.dropdown.MatchFoodItem;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.databinding.FragmentReadymadecoffeeDetailBinding;

public class ReadyMadeCoffeeDetailFragment extends Fragment {

    private FragmentReadymadecoffeeDetailBinding _binding;
    private ReadyMadeCoffeeDetailViewModel _vm;
    private ReadyMadeCoffee _selectedReadymadecoffee;

    //constructor
    public ReadyMadeCoffeeDetailFragment() {
        super(R.layout.fragment_readymadecoffee_detail);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentReadymadecoffeeDetailBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentReadymadecoffeeDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(ReadyMadeCoffeeDetailViewModel.class);

        //get specific item by safe-args
        _selectedReadymadecoffee = ReadyMadeCoffeeDetailFragmentArgs.fromBundle(getArguments()).getSpecReadymadecoffee();
        showDetail(_selectedReadymadecoffee);
        Log.d("YMZdebug", "[ReadyMadeCoffeeDetailFragment.onViewCreated]: ID is " + _selectedReadymadecoffee.getReadymadecoffee_id());

        //edit button listener
        _binding.editButtonReadymadecoffeeDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jump to editFragment with specific item
                ReadyMadeCoffeeDetailFragmentDirections.ActionReadyMadeCoffeeDetailFragmentToReadyMadeCoffeeEditFragment
                        action = ReadyMadeCoffeeDetailFragmentDirections
                                     .actionReadyMadeCoffeeDetailFragmentToReadyMadeCoffeeEditFragment(_selectedReadymadecoffee);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //update view & specific item when complete update in editFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_update_readymadecoffee", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                _selectedReadymadecoffee = (ReadyMadeCoffee) bundle.getSerializable("bundleKey_update_readymadecoffee");
                showDetail(_selectedReadymadecoffee);
            }
        });

        //delete button listener
        _binding.deleteButtonReadymadecoffeeDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_readyMadeCoffeeDetailFragment_to_confirmDeleteFragment);
            }
        });

        //delete or do-nothing when button tapped in dialogFragment
        getParentFragmentManager().setFragmentResultListener("requestKey_confirm_delete", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                int which = bundle.getInt("bundleKey_confirm_delete");
                if(which == DialogInterface.BUTTON_POSITIVE){
                    _vm.delete(_selectedReadymadecoffee);
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

        //go back to list-fragment after delete
        _vm.completeDelete.observe(getViewLifecycleOwner(), completeDelete -> {
            if(completeDelete){
                //avoid from close only dialogFragment, specify go-back-destination fragment
                Navigation.findNavController(view).popBackStack(R.id.coffeeListFragment, false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void showDetail(ReadyMadeCoffee readymadecoffee){
        _binding.nameReadymadecoffeeDetail.setText(readymadecoffee.getReadymadecoffee_name());
        _binding.registeredDateReadymadecoffeeDetail.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", readymadecoffee.getRegistered_time())
        );
        _binding.drinkDateReadymadecoffeeDetail.setText(readymadecoffee.getReadymadecoffee_drink_date());
        _binding.drinkPlaceReadymadecoffeeDetail.setText(readymadecoffee.getReadymadecoffee_drink_place());
        _binding.drinkAmountReadymadecoffeeDetail.setText(Integer.toString(readymadecoffee.getReadymadecoffee_drink_amount()));
        _binding.priceReadymadecoffeeDetail.setText(Integer.toString(readymadecoffee.getReadymadecoffee_price()));
        _binding.drinkMethodTemperatureReadymadecoffeeDatail.setText(
                DrinkMethodTemperatureItem.DrinkMethodTemperature.getType(
                        readymadecoffee.getReadymadecoffee_drink_method_temperature()
                ).getString()
        );
        _binding.drinkMethodTypeReadymadecoffeeDatail.setText(
                DrinkMethodTypeItem.DrinkMethodType.getType(
                        readymadecoffee.getReadymadecoffee_drink_method_type()
                ).getString()
        );
        _binding.tasteSweetRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_sweet());
        _binding.tasteBitterRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_bitter());
        _binding.tasteSourRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_sour());
        _binding.tasteScentRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_scent());
        _binding.tasteBodyRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_body());
        _binding.tasteRichRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_taste_rich());
        _binding.flavorReadymadecoffeeDatail.setText(
                FlavorItem.Flavor.getType(
                        readymadecoffee.getReadymadecoffee_flavor()
                ).getString()
        );
        _binding.matchFoodReadymadecoffeeDatail.setText(
                MatchFoodItem.MatchFood.getType(
                        readymadecoffee.getReadymadecoffee_match_food()
                ).getString()
        );
        _binding.atmosphereReadymadecoffeeDatail.setText(
                AtmosphereItem.Atmosphere.getType(
                        readymadecoffee.getReadymadecoffee_atmosphere()
                ).getString()
        );
        _binding.caffeinelessCheckboxReadymadecoffeeDatail.setChecked(readymadecoffee.getReadymadecoffee_caffeineless());
        _binding.reviewRatingbarReadymadecoffeeDatail.setRating(readymadecoffee.getReadymadecoffee_review());
        _binding.memoReadymadecoffeeDatail.setText(readymadecoffee.getReadymadecoffee_memo());
    }
}
