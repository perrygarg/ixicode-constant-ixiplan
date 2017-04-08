package com.ixicode.constant.ixiplan.permissionhandling;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.fragment.ConfirmationDialogFragment;
import com.ixicode.constant.ixiplan.common.util.AppUtil;


import java.util.HashMap;

public class PermissionManager
{
    private final static String KEY_RATIONAL_LISTENER = "RationaleListener";
    private final static String KEY_ACTIVITY_CONTEXT  = "ActivityContext";
    private final static String KEY_REQUEST_CODE = "RequestCode";
    private final static String KEY_PERMISSION = "Permission";

    /**
     * <br>Firstly, it will check for permission status, if denied then it will check whether there is need to show permission rationale (i.e. Why we need this permission) dialog.
     * <br><code>ActivityCompat.shouldShowRequestPermissionRationale(activityContext, permission);</code> This method returns true if the app has requested this permission previously and the user denied the request.
     * That indicates that you should probably explain to the user why you need the permission.
     * @return
     */
    public static boolean handleRequestForPermission(PermissionRequestModel permissionRequestModel, AppCompatActivity activity)//Activity activityContext, String permission, int requestCode, String msgForPermissionRationale, PermissionRationaleDialogListener listener, boolean isToShowRationale)
    {
        boolean checkPermission = false;
        if(AppUtil.isGreaterThanApi23())
        {
            String permission = permissionRequestModel.permission;

            if(isPermissionGranted(permission, (Activity)activity)) //Permission Granted.
            {
                checkPermission = true;
            }
            else //Request for Permission
            {
                int requestCode = permissionRequestModel.requestCode;
                String msgForPermissionRationale = permissionRequestModel.msgForPermissionRationale;
                boolean isToShowRationale = permissionRequestModel.isToShowRationale;
                PermissionRationaleDialogListener listener = permissionRequestModel.listener;

                boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);

                if(isToShowRationale && shouldShowRequestPermissionRationale)	//if the app has requested this permission previously and the user denied the request without checking "Never ask again".
                {
                    //Show the message that why we need this permission.

                    //Temp Testing
                    HashMap<String, Object> data = new HashMap<>();
                    data.put(KEY_RATIONAL_LISTENER, listener);
                    data.put(KEY_ACTIVITY_CONTEXT, activity);
                    data.put(KEY_REQUEST_CODE, requestCode);
                    data.put(KEY_PERMISSION, permission);
                    ConfirmationDialogFragment dialogFragment = ConfirmationDialogFragment.getInstance(new ConfirmationDialogClickHandler(), data, "" ,msgForPermissionRationale, activity.getString(R.string.label_button_ok_go_ahead), activity.getString(R.string.label_button_not_now), false);
                    dialogFragment.show(activity.getSupportFragmentManager(), "");
                }
                else	//First time asking for the permission
                {
                    requestForPermission(activity, requestCode, permission);
                }
            }
        }
        else
        {
            checkPermission = true;
        }

        return checkPermission;
    }

    /**
     * Handle Request For Multiple Permissions
     * @param activity
     * @return checkPermission
     */
    public static boolean handleRequestForMultiplePermissions(String[] permissions, Activity activity, int requestCode)
    {
        int count = 0;
        boolean checkPermission = false;
        if(AppUtil.isGreaterThanApi23())
        {
            for (int i = 0; i < permissions.length; i++)
            {
                if(isPermissionGranted(permissions[i], activity))
                {
                    count++;
                    permissions[i] = "";
                }
            }
            if(count == permissions.length)
            {
                checkPermission = true;
            }
            else
            {
                requestForPermission(activity, requestCode, permissions);
            }
        }
        else
        {
            checkPermission = true;
        }
        return checkPermission;
    }

    /**
     * Is Permission Granted
     * @param permission
     * @return true if permission Granted otherwise false
     */
    public static boolean isPermissionGranted(String permission, Context context)
    {
        boolean isRequestGranted = false;
        int permissionStatus = ContextCompat.checkSelfPermission(context, permission);
        if(permissionStatus == PackageManager.PERMISSION_GRANTED)
        {
            isRequestGranted = true;
        }

        return isRequestGranted;
    }

    /**
     * requestForPermission
     * This method is requesting for particular given permission.
     * @param activityContext
     * @param requestCode
     * @param permission
     */
    private static void requestForPermission(Activity activityContext, int requestCode, String permission)
    {
        String[] permissionStringArray = {permission};

        ActivityCompat.requestPermissions((Activity)activityContext, permissionStringArray, requestCode);
    }

    /**
     * requestForPermission
     * This method is requesting for multiple permissions.
     * @param activityContext
     * @param requestCode
     */
    private static void requestForPermission(Activity activityContext, int requestCode, String permission[])
    {
        ActivityCompat.requestPermissions(activityContext, permission, requestCode);
    }

   /**//**
 * Get All Permissions for App
 * @param activity
 *//*
    public static void getAllPermissionsForApp(Activity activity)
    {
        String permissionArray[] = new String[2];
        if(AppUtil.isAboveLollipop())
        {
            permissionArray[0] = !isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION, activity) ? Manifest.permission.ACCESS_FINE_LOCATION : "";
            permissionArray[1] = !isPermissionGranted(Manifest.permission.RECEIVE_SMS, activity) ? Manifest.permission.RECEIVE_SMS : "";
            requestForPermission(activity, AppConstant.KEY_REQUEST_ALL_PERMISSIONS, permissionArray);
        }
    }*/

    /**
     *
     */
    static class ConfirmationDialogClickHandler implements ConfirmationDialogFragment.ConfirmationDialogListener
    {
        @Override
        public void onDialogButtonClick(HashMap<String, Object> data, boolean isActionConfirmed)
        {
            PermissionRationaleDialogListener listener = (PermissionRationaleDialogListener) data.get(KEY_RATIONAL_LISTENER);
            Activity activityContext = (Activity) data.get(KEY_ACTIVITY_CONTEXT);
            int requestCode = (int) data.get(KEY_REQUEST_CODE);
            String permission = (String) data.get(KEY_PERMISSION);

            if(isActionConfirmed)
            {
                requestForPermission(activityContext, requestCode, permission);
            }
            else
            {
                if(listener != null)
                    listener.onCancelPermissionRationale(requestCode);
            }
        }
    }
}
