package ymz.coffeerep.scenes.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ymz.coffeerep.R;
import ymz.coffeerep.databinding.FragmentRawbeansDetailBinding;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}