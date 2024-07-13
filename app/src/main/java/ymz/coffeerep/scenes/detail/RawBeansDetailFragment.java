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

public class RawBeansDetailFragment extends Fragment {

    private FragmentRawbeansDetailBinding _binding;
    private RawBeansDetailViewModel _vm;

    private TextView RawBeansNameView;
    private TextView RawBeansCountryView;

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

        initTextView();

        return view;
    }

    //processes right after the view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding = FragmentRawbeansDetailBinding.bind(view);

        _vm = new ViewModelProvider(this).get(RawBeansDetailViewModel.class);

        RawBeans selectedRawBeans = RawBeansDetailFragmentArgs.fromBundle(getArguments()).getRawbeans();
        Log.d("YMZdebug", "[RawBeansDetailFragment.onViewCreated]: ID is " + selectedRawBeans.getRawbeans_id());
        showDetail(selectedRawBeans);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void initTextView(){
        RawBeansNameView = _binding.nameRawbeansDetail;
        RawBeansCountryView = _binding.countryRawbeansDatail;
    }

    private void showDetail(RawBeans rawbeans){
        RawBeansNameView.setText(rawbeans.getRawbeans_name());
        RawBeansCountryView.setText(rawbeans.getRawbeans_country());
    }
}