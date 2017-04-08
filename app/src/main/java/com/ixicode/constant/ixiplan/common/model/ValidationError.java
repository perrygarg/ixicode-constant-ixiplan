package com.ixicode.constant.ixiplan.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akash.singla on 12/17/2015.
 */
public class ValidationError
{
    @SerializedName("param")
    public String param = null;

    @SerializedName("msg")
    public String msg = null;

    @SerializedName("value")
    public String value = null;

    @SerializedName("code")
    public String code = null;
}
