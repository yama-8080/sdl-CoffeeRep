package ymz.coffeerep.scenes.list;

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

import ymz.coffeerep.R;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.rawbeans.RawBeansListAdapter;
import ymz.coffeerep.databinding.FragmentBeansListBinding;

public class BeansListFragment extends Fragment {

    private FragmentBeansListBinding _binding;
    private BeansListViewModel _vm;

    //constructor
    public BeansListFragment() {
        super(R.layout.fragment_beans_list);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentBeansListBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentBeansListBinding.bind(view);

        final RawBeansListAdapter adapter = new RawBeansListAdapter(new RawBeansListAdapter.WordDiff());
        _binding.recyclerRawbeansList.setAdapter(adapter);

        _vm = new ViewModelProvider(this).get(BeansListViewModel.class);

        //add an observer on the LiveData (fired every time the data changes)
        _vm.getAllRawBeans().observe(getViewLifecycleOwner(), rawbeans -> {
            adapter.submitList(rawbeans);
        });

        //send specific item to detail fragment when it selected
        adapter.selected.observe(getViewLifecycleOwner(), selected -> {
            if(selected){
                //get data
                RawBeans selectedRawBeans = adapter.get_selectedRawBeans();

                //debug log
                if(selectedRawBeans.getRawbeans_name().isEmpty()){
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is EMPTY");
                }
                else{
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is " + selectedRawBeans.getRawbeans_name());
                }

                //jump with sending data
                BeansListFragmentDirections.ActionBeansListFragmentToRawBeansDetailFragment action
                        = BeansListFragmentDirections.actionBeansListFragmentToRawBeansDetailFragment(
                                selectedRawBeans
                );
                Navigation.findNavController(view).navigate(action);

                adapter.set_selectedRawBeans(null);
                adapter.selected.setValue(false);
            }
        });

        _binding.buttonBeansListToInsertRawbeans.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_beansListFragment_to_rawBeansInsertFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
