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
	int WS_CODE_FETCH_MODES_BW_PLACES = 2;
	int WS_CODE_FETCH_MODES_TRENDING_PLACES = 3;

	/**
	 * Web Service URLs
	 */
	//String BASE_URL = ""; //Production Server
	String BASE_URL = "http://build2.ixigo.com/"; //Local Test Server
    String AUTOCOMPLETE_PLACE_URL = BASE_URL + "action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=%1$s";
	String FETCH_MODES_BETWEEN_PLACES_URL = BASE_URL + "api/v2/a2b/modes?apiKey=ixicode!2$&originCityId=%1$s&destinationCityId=%2$s";

	String TRENDING_PLACES_API = BASE_URL + "api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$";


}
