package com.ixicode.constant.ixiplan.permissionhandling;

public class PermissionRequestModel
{
	public String permission;
	public int	requestCode;
	public String	msgForPermissionRationale;
	public PermissionRationaleDialogListener listener;
	public boolean	isToShowRationale;

	public PermissionRequestModel(String permission, int requestCode, String msgForPermissionRationale, PermissionRationaleDialogListener listener, boolean isToShowRationale)
	{
		this.permission = permission;
		this.requestCode = requestCode;
		this.msgForPermissionRationale = msgForPermissionRationale;
		this.listener = listener;
		this.isToShowRationale = isToShowRationale;
	}
}
