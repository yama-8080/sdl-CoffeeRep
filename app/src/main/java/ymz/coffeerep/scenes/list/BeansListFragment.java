package ymz.coffeerep.scenes.list;

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
import ymz.coffeerep.data.rawbeans.RawBeansListAdapter;
import ymz.coffeerep.databinding.FragmentBeansListBinding;

public class BeansListFragment extends Fragment {

    private FragmentBeansListBinding binding;
    private BeansListViewModel vm;

    //constructor
    public BeansListFragment() {
        super(R.layout.fragment_beans_list);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentBeansListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBeansListBinding.bind(view);

        final RawBeansListAdapter adapter = new RawBeansListAdapter(new RawBeansListAdapter.WordDiff());
        binding.recyclerRawbeansList.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BeansListViewModel.class);

        // add an observer on the LiveData (fired every time the data changes)
        vm.getAllRawBeans().observe(getViewLifecycleOwner(), rawbeans -> {
            adapter.submitList(rawbeans);
        });

        /*
        binding.fabHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_defaultInsertFragment);
            }
        });

        binding.buttonHomeToRawbeansInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_rawBeansInsertFragment);
            }
        });

        binding.buttonHomeToRawbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_rawBeansDetailFragment);
            }
        });

         */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}