package com.ixicode.constant.ixiplan.common.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class ConfirmationDialogFragment extends DialogFragment
{
	private HashMap<String, Object> data;
	private String title;
	private String message;
	private String positiveButtonLabel;
	private String negativeButtonLabel;
	private boolean isCancelable;

	private ConfirmationDialogListener listener;

	public interface ConfirmationDialogListener
	{
		public abstract void onDialogButtonClick(HashMap<String, Object> data, boolean isActionConfirmed);
	}

	//Get Instance
	public static ConfirmationDialogFragment getInstance(ConfirmationDialogListener listener, HashMap<String, Object> data, String title, String message, String positiveButtonLabel, String negativeButtonLable, boolean isCancelable)
	{
		ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();

		fragment.data = data;
		fragment.title = title;
		fragment.message = message;
		fragment.positiveButtonLabel = positiveButtonLabel;
		fragment.negativeButtonLabel = negativeButtonLable;
		fragment.isCancelable = isCancelable;
		fragment.listener = listener;

		return  fragment;
	}

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		//Instantiate Alert Dialog Builder Instance
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setCancelable(isCancelable);

		//Set Positive button
		builder.setPositiveButton(positiveButtonLabel, new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(listener != null)
					listener.onDialogButtonClick(data, true);
			}
		});

		//Set Negative Button
		builder.setNegativeButton(negativeButtonLabel, new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(listener != null)
					listener.onDialogButtonClick(data, false);
			}
		});

		return builder.create();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		getDialog().setCanceledOnTouchOutside(isCancelable);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
