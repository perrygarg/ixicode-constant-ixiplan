package com.ixicode.constant.ixiplan.common.network;

public interface WebConstants
{
	/**
	 * Request Headers -  Key and Value
	 */
	String HEADER_ACCEPT_ENCODING_KEY = "Accept-Encoding";
	String HEADER_ACCEPT_ENCODING_VALUE = "gzip";

	String HEADER_API_ID_KEY = "API-ID";
	String HEADER_API_ID_VALUE = "1";

	String HEADER_API_VERSION_KEY = "API-VERSION";
	String HEADER_API_VERSION_VALUE = "1.0";

	String HEADER_DEVICE_KEY = "API-DEVICE";
	String HEADER_DEVICE_VALUE = "android";

	String HEADER_DEVICE_ID_KEY = "API-DEVICE-ID";

	String HEADER_API_TOKEN_KEY = "API-TOKEN";

	/**
	 * Web Service Task Codes
	 */
	int WS_CODE_AUTOCOMPLETE_PLACE = 1;
	int WS_CODE_FORGOT_PASSWORD = 2;
	int WS_CODE_RESET_PASSWORD = 3;
	int WS_CODE_SEARCH_SUGGESTION = 4;
	int WS_CODE_SEARCH_CLAIM_LIST = 5;

	/**
	 * Web Service URLs
	 */
	//String BASE_URL = ""; //Production Server
	String BASE_URL = "http://build2.ixigo.com/"; //Local Test Server
    String AUTOCOMPLETE_PLACE_URL = BASE_URL + "action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=";



}
