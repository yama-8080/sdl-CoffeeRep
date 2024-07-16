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
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.roastbeans.RoastBeansListAdapter;
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

        _vm = new ViewModelProvider(this).get(BeansListViewModel.class);

        final RawBeansListAdapter rawbeansAdapter = new RawBeansListAdapter(new RawBeansListAdapter.WordDiff());
        _binding.recyclerRawbeansList.setAdapter(rawbeansAdapter);
        final RoastBeansListAdapter roastbeansAdapter = new RoastBeansListAdapter(new RoastBeansListAdapter.WordDiff());
        _binding.recyclerRoastbeansList.setAdapter(roastbeansAdapter);

        //add an observer on the rawbeans LiveData (fired every time the data changes)
        _vm.getAllRawBeans().observe(getViewLifecycleOwner(), rawbeans -> {
            rawbeansAdapter.submitList(rawbeans);
        });

        //send specific rawbeans item to detail fragment when it selected
        rawbeansAdapter.selected.observe(getViewLifecycleOwner(), selected -> {
            if(selected){
                //get specific item from ListAdapter
                RawBeans selectedRawbeans = rawbeansAdapter.get_selectedRawBeans();

                //debug log
                if(selectedRawbeans.getRawbeans_name().isEmpty()){
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is EMPTY");
                }
                else{
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is " + selectedRawbeans.getRawbeans_name());
                }

                //jump next with specific item
                BeansListFragmentDirections.ActionBeansListFragmentToRawBeansDetailFragment
                        action = BeansListFragmentDirections.actionBeansListFragmentToRawBeansDetailFragment(selectedRawbeans);
                Navigation.findNavController(view).navigate(action);

                rawbeansAdapter.set_selectedRawBeans(null);
                rawbeansAdapter.selected.setValue(false);
            }
        });

        //register rawbeans button listener
        _binding.buttonBeansListToRawbeansRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_beansListFragment_to_rawBeansRegisterFragment);
            }
        });



        //add an observer on the roastbeans LiveData (fired every time the data changes)
        _vm.getAllRoastBeans().observe(getViewLifecycleOwner(), roastbeans -> {
            roastbeansAdapter.submitList(roastbeans);
        });

        //send specific roastbeans item to detail fragment when it selected
        roastbeansAdapter.selected.observe(getViewLifecycleOwner(), selected -> {
            if(selected){
                //get specific item from ListAdapter
                RoastBeans selectedRoastbeans = roastbeansAdapter.get_selectedRoastBeans();

                //debug log
                if(selectedRoastbeans.getRoastbeans_name().isEmpty()){
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is EMPTY");
                }
                else{
                    Log.d("YMZdebug", "[BeansListFragment.onViewCreated]: name is " + selectedRoastbeans.getRoastbeans_name());
                }

                //jump next with specific item
                BeansListFragmentDirections.ActionBeansListFragmentToRoastBeansDetailFragment
                        action = BeansListFragmentDirections.actionBeansListFragmentToRoastBeansDetailFragment(selectedRoastbeans);
                Navigation.findNavController(view).navigate(action);

                roastbeansAdapter.set_selectedRoastBeans(null);
                roastbeansAdapter.selected.setValue(false);
            }
        });

        //register roastbeans button listener
        _binding.buttonBeansListToRoastbeansRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_beansListFragment_to_roastBeansRegisterFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
