package ymz.coffeerep.scenes.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ymz.coffeerep.R;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.databinding.FragmentRawbeansInsertBinding;

public class RawBeansInsertFragment extends Fragment {


    private FragmentRawbeansInsertBinding binding;
    private RawBeansInsertViewModel vm;

    //constructor
    public RawBeansInsertFragment() {
        super(R.layout.fragment_rawbeans_insert);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentRawbeansInsertBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRawbeansInsertBinding.bind(view);

        vm = new ViewModelProvider(this).get(RawBeansInsertViewModel.class);

        binding.fabRawbeansInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insert();
                Navigation.findNavController(view).navigate(R.id.action_rawBeansInsertFragment_to_beansListFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void insert() {
        String name = binding.nameRawbeansInsert.getText().toString();
        String country = binding.countryRawbeansInsert.getText().toString();
        long time = System.currentTimeMillis();
        RawBeans newRawbeans = new RawBeans(name, country, time);
        vm.insert(newRawbeans);
    }
}
