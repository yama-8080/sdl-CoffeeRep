package ymz.coffeerep.scenes.register;

import android.os.Bundle;
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
import ymz.coffeerep.databinding.FragmentRawbeansRegisterBinding;

public class RawBeansRegisterFragment extends Fragment {


    private FragmentRawbeansRegisterBinding _binding;
    private RawBeansRegisterViewModel _vm;

    //constructor
    public RawBeansRegisterFragment() {
        super(R.layout.fragment_rawbeans_register);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansRegisterBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansRegisterBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansRegisterViewModel.class);

        //check if the inputs are correct
        _vm.errorMsg.observe(getViewLifecycleOwner(), msg -> {
            if(!msg.isEmpty()){
                Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show();
                _vm.errorMsg.setValue("");
            }
        });

        //go back to previous fragment when complete insert
        _vm.complete.observe(getViewLifecycleOwner(), complete -> {
            if(complete){
                Navigation.findNavController(view).popBackStack();
            }
        });

        //insert
        _binding.insertFabRawbeansRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insert();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void insert() {
        String name = _binding.nameRawbeansRegister.getText().toString();
        String country = _binding.countryRawbeansRegister.getText().toString();
        long time = System.currentTimeMillis();
        RawBeans newRawbeans = new RawBeans(name, country, time);
        _vm.insert(newRawbeans);
    }
}
