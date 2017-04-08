package com.ixicode.constant.ixiplan.common.network;

/**
 * Web Service Constants
 * 
 * @author Jaswinder Singh
 *
 */
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
	int WS_CODE_LOGIN = 1;
	int WS_CODE_FORGOT_PASSWORD = 2;
	int WS_CODE_RESET_PASSWORD = 3;
	int WS_CODE_SEARCH_SUGGESTION = 4;
	int WS_CODE_SEARCH_CLAIM_LIST = 5;

	/**
	 * Web Service URLs
	 */
	//String BASE_URL = ""; //Production Server
	String BASE_URL = "http://labs.infoedge.com:9313/"; //Local Test Server
	//String URL_LOGIN = BASE_URL + "v1/business/login";
    String URL_LOGIN = BASE_URL + "v1/user/session";
	String URL_FORGOT_PASSWORD = BASE_URL + "v1/business/forgotPassword";
    String URL_RESET_PASSWORD = BASE_URL + "v1/business/resetPassword";
	String URL_LISTING_SUGGESTOR = BASE_URL + "v1/business/listingSuggestor";
	String URL_SEARCH_CLAIM_LIST = BASE_URL + "v1/business/searchListing";


}
