package ymz.coffeerep.scenes.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ymz.coffeerep.R;
import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.databinding.FragmentRawbeansDetailBinding;
import ymz.coffeerep.scenes.list.BeansListFragmentDirections;

public class RawBeansDetailFragment extends Fragment {

    private FragmentRawbeansDetailBinding _binding;
    private RawBeansDetailViewModel _vm;

    //constructor
    public RawBeansDetailFragment() {
        super(R.layout.fragment_rawbeans_detail);
    }

    //processes right before the view creating
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        _binding = FragmentRawbeansDetailBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansDetailViewModel.class);

        //get specific item by safe-args
        RawBeans selectedRawbeans = RawBeansDetailFragmentArgs.fromBundle(getArguments()).getSpecRawbeans();
        showDetail(selectedRawbeans);
        Log.d("YMZdebug", "[RawBeansDetailFragment.onViewCreated]: ID is " + selectedRawbeans.getRawbeans_id());

        _binding.editButtonRawbeansDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jump next with specific item
                RawBeansDetailFragmentDirections.ActionRawBeansDetailFragmentToRawBeansEditFragment
                        action = RawBeansDetailFragmentDirections.actionRawBeansDetailFragmentToRawBeansEditFragment(selectedRawbeans);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void showDetail(RawBeans rawbeans){
        _binding.nameRawbeansDetail.setText(rawbeans.getRawbeans_name());
        _binding.countryRawbeansDatail.setText(rawbeans.getRawbeans_country());
    }
}