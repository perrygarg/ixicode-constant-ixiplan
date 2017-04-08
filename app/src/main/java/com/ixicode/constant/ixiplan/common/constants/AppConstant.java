package com.ixicode.constant.ixiplan.common.constants;

public interface AppConstant
{
	int ERROR_TYPE_SIMPLE = 1;
	int ERROR_TYPE_NO_NETWORK = 2;

	String CITIES_IDS = "CITIES_IDS";

	//App Store Url
	String PLAYSTORE_HTTPS_URL = "https://play.google.com/store/apps/details?id=";
	String PLAYSTORE_APP_URL = "market://details?id=";

	//Category Constants, Synced With WebEnd, Also Used as "Product Cat Id" in Deals Response for Showing Background Images.
	int VENDOR_CATEGORY_SERVICE = 1;
	int VENDOR_CATEGORY_BREAK_DOWN = 2;
	int VENDOR_CATEGORY_BATTERY = 3;
	int VENDOR_CATEGORY_TYRES = 4;
	int VENDOR_CATEGORY_GAS = 5;
	int VENDOR_CATEGORY_ACCESSORIES = 6;

	//Coming From Key
	String KEY_INTENT_COMING_FROM = "coming_from";
	String KEY_INTENT_WEBVIEW_URL = "webview_url";
	String KEY_INTENT_SEARCH_TERM_NAME = "search_term_name";
	String KEY_INTENT_SEARCH_TERM_VALUE = "search_term_value";
	String KEY_INTENT_VENDOR_CATEGORY = "vendor_category";

	/**
	 * Request Codes
	 */
	int REQUEST_CODE_FINISH_ACTIVITY = 101;


	/**
	 * Activity Specific
	 */


	/**
	 * Local Urls
	 */
	//Urls for Terms & Conditions and Privacy Policy
	String URL_ASSET = "file:///android_asset/";
	String URL_ASSET_HTML_FOLDER = URL_ASSET + "html/";
	String TERMS_AND_CONDITIONS = "TermsAndConditions.html";
	String PRIVACY_POLICY = "PrivacyPolicy.html";

	String URL_TERMS_AND_CONDITIONS = URL_ASSET_HTML_FOLDER + TERMS_AND_CONDITIONS;
	String URL_PRIVACY_AND_POLICY =  URL_ASSET_HTML_FOLDER + PRIVACY_POLICY;

	/**
	 * GCM and Google Maps
	 */
	//GCM Implementation
	String GCM_SENDER_ID_NUMBER =  "";
}
