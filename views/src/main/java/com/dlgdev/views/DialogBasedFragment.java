package com.dlgdev.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class DialogBasedFragment extends DialogFragment {
	Dialog dialog;

	public static DialogBasedFragment createWithDialog(Dialog dialog) {
		DialogBasedFragment fragment = new DialogBasedFragment();
		fragment.dialog = dialog;
		return fragment;
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		return dialog;
	}
}
