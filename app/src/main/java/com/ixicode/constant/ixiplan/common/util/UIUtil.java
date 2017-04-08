package com.ixicode.constant.ixiplan.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.provider.Settings;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ixicode.constant.ixiplan.common.util.customprogress.CProgressHUD;


public class UIUtil
{
	private static Toast toast;
	private static boolean isShowingGotoSetting;

	/********** Hide / Show Soft Keyboard ***********/

	//Show Soft Keyboard
	public static void showSoftKeyboard(EditText editText)
	{
		//Request Focus
		editText.requestFocus();

		InputMethodManager inputMethodManager = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}

	//Hide Soft Keyboard
	public static void hideSoftKeyboard(Activity activity)
	{
		//Get View
		View view = activity.getWindow().getDecorView();

		//Hide Soft Keyboard
		hideSoftKeyboard(view);
	}

	//Hide Soft Keyboard
	public static void hideSoftKeyboard(View view)
	{
		if(view == null)
		{
			return;
		}

		//Get Window Token
		IBinder token = view.getWindowToken();

		//Get InputMethodManager
		InputMethodManager inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

		//Gain Focus on Current View
		view.requestFocus();

		//Hide Soft Input
		inputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.RESULT_UNCHANGED_SHOWN);
	}

	/************ Show Toast ************/

	//Show Toast
	public static void showToast(int messageId, Context context)
	{
		showToast(context.getString(messageId), context);
	}

	//Show Toast
	public static void showToast(String message, Context context)
	{
		//Check Toast
		if(toast != null)
		{
			//Get View of Toast
			View view = toast.getView();

			if(view.isShown())
			{
				return;
			}
		}

		//Create Toast
		toast = Toast.makeText(context, message, Toast.LENGTH_LONG);

		//Show toast
		toast.show();
	}

	/************* Progress Dialog *************/

	/**
	 * Show Progress Dialog
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param isInDeterminent
	 * @param isCancelable
	 * @return
	 */
	public static ProgressDialog showProgressDialog(Context context, String title, String message, boolean isInDeterminent, boolean isCancelable)
	{
		return (ProgressDialog.show(context, title, message, isInDeterminent, isCancelable));
	}

	/**
	 * Shows Progress Bar Dialog
	 *
	 * @param activityContext
	 * @return
	 */
	public static CProgressHUD showCustomProgress(Context activityContext) {
		CProgressHUD dialog = null;

		dialog = CProgressHUD.create(activityContext);
		dialog.show();

		return dialog;
	}

	public static void dismissCustomProgress(CProgressHUD dialog) {
		try {
			if (dialog != null) {
				dialog.dismiss();
			}
		} catch (Exception exception) {
			//Consume it
		}
	}

	/**
	 * Shows Progress Bar Dialog
	 * @param activityContext
	 * @return
	 */
	public static Dialog showProgressDialog(Context activityContext)
	{
		Dialog dialog = null;

		dialog = showProgressDialog(activityContext, "", "", true, false);

		return dialog;
	}

	public static void dismissDialog(Dialog dialog)
	{
		try
		{
			if (dialog != null)
			{
				dialog.dismiss();
			}
		}
		catch (Exception exception)
		{
			//Consume it
		}
	}

	/*********** Custom Progress Dialog ********************/

	/**
	 *  Custom Progress Dialog
	 *
	 * @param context
	 * @return
	 */
	public static Dialog showCustomProgressDialog(Context context)
	{
		Dialog dialog = new Dialog(context);

		// Todo show Custom dialog.

		return dialog;
	}


	/**
	 * Show SnackBar
	 *
	 * @param view
	 * @param message
	 */
	public static Snackbar showSnacksbar(View view, String message, boolean isIndefinite)
	{
		int duration = 0;

		if(isIndefinite)
			duration = Snackbar.LENGTH_INDEFINITE;
		else
			duration = Snackbar.LENGTH_LONG;

		Snackbar snackbar = Snackbar.make(view, message, duration);
		snackbar.show();
		return snackbar;
	}

	/**
	 * Show SnackBar
	 *
	 * @param view
	 * @param message
	 */
	public static Snackbar showSnacksbar(View view, String message, String action, View.OnClickListener onClickListener)
	{
		Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
		snackbar.setAction(action, onClickListener);
		snackbar.show();

		return snackbar;
	}

//	/************ Alert Dialog *************/
//
//	/**
//	 * Prepares and shows the Alert Dialog
//	 * @param activity
//	 */
//	public static void showAlertDialog(final Activity activity, String title, String message)
//	{
//		String alertDialogButtonTextOk = activity.getString(R.string.button_ok);
//
//		final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
//
//		//Set Title;
//		alertDialog.setTitle(title);
//
//		// Setting Dialog Message
//		alertDialog.setMessage(message);
//
//		// Setting OK Button
//		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, alertDialogButtonTextOk, new DialogInterface.OnClickListener()
//		{
//			public void onClick(DialogInterface dialog, int which)
//			{
//				UIUtil.dismissDialog(alertDialog);
//			}
//		});
//
//		// Showing Alert Message
//		alertDialog.show();
//	}

//	/*************** Show Location Settings Dialog ********/
//
//	/**
//	 * Show AlertDialog : Location Services Turned On
//	 * @param context
//	 * @return
//	 */
//	public static AlertDialog enableLocation(final Context context)
//	{
//		if(isShowingGotoSetting)
//		{
//			return null;
//		}
//
//		//Set Flag
//		isShowingGotoSetting = true;
//
//		//Show AlertDialog
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle(R.string.location_services_title);
//		builder.setMessage(R.string.location_services_message);
//		builder.setCancelable(false);
//
//		builder.setPositiveButton(R.string.button_settings, new DialogInterface.OnClickListener()
//		{
//			@Override
//			public void onClick(DialogInterface dialog, int which)
//			{
//				//Dismiss Dialog
//				dialog.dismiss();
//
//				//Goto WiFi Settings
//				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//
//				// Verify that the intent will resolve to an activity
//				if (intent.resolveActivity(context.getPackageManager()) != null)
//				{
//					context.startActivity(intent);
//				}
//
//				//Set Flag
//				isShowingGotoSetting = false;
//			}
//		});
//
//		builder.setNegativeButton(R.string.button_dismiss, new DialogInterface.OnClickListener()
//		{
//			@Override
//			public void onClick(DialogInterface dialog, int which)
//			{
//				//Dismiss Dialog
//				dialog.dismiss();
//
//				//Set Flag
//				isShowingGotoSetting = false;
//			}
//		});
//
//		//Get AlertDialog
//		AlertDialog alertDialog = builder.create();
//
//		//Show AlertDialog
//		alertDialog.show();
//
//		return alertDialog;
//	}

    /******** Show Error View ********/

//	public static void handleErrorDisplay(ErrorDisplay errorDisplay, View viewParent,
//										  View viewErrorTemplate, Context context)
//	{
//		if(errorDisplay == null)
//			return ;
//
//        if(viewErrorTemplate != null)
//    		viewErrorTemplate.setVisibility(View.VISIBLE);
//
//		if(errorDisplay.isSnakbarShown && !AppUtil.isStringEmpty(errorDisplay.errorMsg))
//		{
//			if(viewErrorTemplate != null)
//			{
//				viewErrorTemplate.setVisibility(View.INVISIBLE);
//			}
//
//            String errorMsg = (AppUtil.isStringEmpty(errorDisplay.errorMsg) ? context.getString(R.string.error_tech_error) : errorDisplay.errorMsg);
//
//			showSnacksbar(viewParent, errorMsg, false);
//		}
//		else if(viewErrorTemplate != null)
//		{
//			viewErrorTemplate.setVisibility(View.VISIBLE);
//
//			ImageView imageViewFullError = (ImageView) viewErrorTemplate.findViewById(R.id.imageViewFullError);
//			TextView textViewFullError = (TextView) viewErrorTemplate.findViewById(R.id.textViewFullError);
//			Button buttonFullError = (Button) viewErrorTemplate.findViewById(R.id.buttonFullError);
//
//			if(AppUtil.isStringEmpty(errorDisplay.buttonMsg))
//			{
//				buttonFullError.setVisibility(View.GONE);
//			}
//			else
//			{
//				buttonFullError.setVisibility(View.VISIBLE);
//				buttonFullError.setText(errorDisplay.buttonMsg);
//			}
//
//			if(AppUtil.isStringEmpty(errorDisplay.errorMsg))
//			{
//				textViewFullError.setVisibility(View.GONE);
//			}
//			else
//			{
//				textViewFullError.setVisibility(View.VISIBLE);
//				textViewFullError.setText(errorDisplay.errorMsg);
//			}
//
//			if(errorDisplay.errorDrawable == 0)
//			{
//				imageViewFullError.setVisibility(View.GONE);
//			}
//			else
//			{
//				imageViewFullError.setVisibility(View.VISIBLE);
//				imageViewFullError.setImageResource(errorDisplay.errorDrawable);
//			}
//
//		}
//	}
//
//	public static int getErrorResource(int type)
//	{
//		int resourceId = 0;
//
//		switch (type)
//		{
//			case AppConstant.ERROR_TYPE_NO_NETWORK:
//
//				resourceId = R.drawable.img_no_internet_connection;
//
//				break;
//
//			default:
//
//				resourceId = R.drawable.img_no_result_found;
//
//		}
//
//		return resourceId;
//	}
}
