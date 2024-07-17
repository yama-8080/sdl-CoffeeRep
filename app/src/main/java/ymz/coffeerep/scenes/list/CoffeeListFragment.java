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
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeListAdapter;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffeeListAdapter;
import ymz.coffeerep.databinding.FragmentCoffeeListBinding;

public class CoffeeListFragment extends Fragment {

    private FragmentCoffeeListBinding _binding;
    private CoffeeListViewModel _vm;

    //constructor
    public CoffeeListFragment() {
        super(R.layout.fragment_coffee_list);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentCoffeeListBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentCoffeeListBinding.bind(view);

        _vm = new ViewModelProvider(this).get(CoffeeListViewModel.class);

        final ReadyMadeCoffeeListAdapter readymadecoffeeAdapter = new ReadyMadeCoffeeListAdapter(new ReadyMadeCoffeeListAdapter.WordDiff());
        _binding.recyclerReadymadecoffeeList.setAdapter(readymadecoffeeAdapter);
        final SelfDripCoffeeListAdapter selfdripcoffeeAdapter = new SelfDripCoffeeListAdapter(new SelfDripCoffeeListAdapter.WordDiff());
        _binding.recyclerSelfdripcoffeeList.setAdapter(selfdripcoffeeAdapter);

        //add an observer on the readymadecoffee LiveData (fired every time the data changes)
        _vm.getAllReadyMadeCoffee().observe(getViewLifecycleOwner(), readymadecoffee -> {
            readymadecoffeeAdapter.submitList(readymadecoffee);
        });

        //send specific readymadecoffee item to detail fragment when it selected
        readymadecoffeeAdapter.selected.observe(getViewLifecycleOwner(), selected -> {
            if(selected){
                //get specific item from ListAdapter
                ReadyMadeCoffee selectedReadymadecoffee = readymadecoffeeAdapter.get_selectedReadyMadeCoffee();

                //debug log
                if(selectedReadymadecoffee.getReadymadecoffee_name().isEmpty()){
                    Log.d("YMZdebug", "[CoffeeListFragment.onViewCreated]: name is EMPTY");
                }
                else{
                    Log.d("YMZdebug", "[CoffeeListFragment.onViewCreated]: name is " + selectedReadymadecoffee.getReadymadecoffee_name());
                }

                //jump next with specific item
                CoffeeListFragmentDirections.ActionCoffeeListFragmentToReadyMadeCoffeeDetailFragment
                        action = CoffeeListFragmentDirections.actionCoffeeListFragmentToReadyMadeCoffeeDetailFragment(selectedReadymadecoffee);
                Navigation.findNavController(view).navigate(action);

                readymadecoffeeAdapter.set_selectedReadyMadeCoffee(null);
                readymadecoffeeAdapter.selected.setValue(false);
            }
        });

        //register readymadecoffee button listener
        _binding.buttonCoffeeListToReadymadecoffeeRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_coffeeListFragment_to_readyMadeCoffeeRegisterFragment);
            }
        });



        //add an observer on the selfdripcoffee LiveData (fired every time the data changes)
        _vm.getAllSelfDripCoffee().observe(getViewLifecycleOwner(), selfdripcoffee -> {
            selfdripcoffeeAdapter.submitList(selfdripcoffee);
        });

        //send specific selfdripcoffee item to detail fragment when it selected
        selfdripcoffeeAdapter.selected.observe(getViewLifecycleOwner(), selected -> {
            if(selected){
                //get specific item from ListAdapter
                SelfDripCoffee selectedSelfdripcoffee = selfdripcoffeeAdapter.get_selectedSelfDripCoffee();

                //debug log
                if(selectedSelfdripcoffee.getSelfdripcoffee_name().isEmpty()){
                    Log.d("YMZdebug", "[CoffeeListFragment.onViewCreated]: name is EMPTY");
                }
                else{
                    Log.d("YMZdebug", "[CoffeeListFragment.onViewCreated]: name is " + selectedSelfdripcoffee.getSelfdripcoffee_name());
                }

                //jump next with specific item
                //TODO
                //CoffeeListFragmentDirections.ActionCoffeeListFragmentToSelfDripCoffeeDetailFragment
                //        action = CoffeeListFragmentDirections.actionCoffeeListFragmentToSelfDripCoffeeDetailFragment(selectedSelfdripcoffee);
                //Navigation.findNavController(view).navigate(action);

                selfdripcoffeeAdapter.set_selectedSelfDripCoffee(null);
                selfdripcoffeeAdapter.selected.setValue(false);
            }
        });

        //register selfdripcoffee button listener
        _binding.buttonCoffeeListToSelfdripcoffeeRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO
                //Navigation.findNavController(view).navigate(R.id.action_coffeeListFragment_to_selfDripCoffeeRegisterFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
