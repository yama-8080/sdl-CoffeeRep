package ymz.coffeerep.scenes.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import ymz.coffeerep.R;

public class ConfirmDeleteFragment extends DialogFragment {

    public static String TAG = "PurchaseConfirmationDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.confirm_delete))
                .setPositiveButton(getString(R.string.ok), new ConfirmDeleteDialogListener())
                .setNegativeButton(getString(R.string.cancel), new ConfirmDeleteDialogListener())
                .create();
    }

    private class ConfirmDeleteDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Bundle result = new Bundle();
            result.putInt("bundleKey_confirm_delete", which);
            getParentFragmentManager().setFragmentResult("requestKey_confirm_delete", result);
        }
    }
}
