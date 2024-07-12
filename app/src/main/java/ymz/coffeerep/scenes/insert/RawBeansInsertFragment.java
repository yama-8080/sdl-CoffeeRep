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


    private FragmentRawbeansInsertBinding _binding;
    private RawBeansInsertViewModel _vm;

    //constructor
    public RawBeansInsertFragment() {
        super(R.layout.fragment_rawbeans_insert);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansInsertBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansInsertBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansInsertViewModel.class);

        _binding.fabRawbeansInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insert();
                Navigation.findNavController(view).navigate(R.id.action_rawBeansInsertFragment_to_beansListFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void insert() {
        String name = _binding.nameRawbeansInsert.getText().toString();
        String country = _binding.countryRawbeansInsert.getText().toString();
        long time = System.currentTimeMillis();
        RawBeans newRawbeans = new RawBeans(name, country, time);
        _vm.insert(newRawbeans);
    }
}
