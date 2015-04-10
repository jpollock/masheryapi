package com.mashery.pm.api.sdk.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.Arrays;




public class ReportingDataService  extends MasheryAPIBase {
	

	public ReportingDataService() throws Exception {
		super();
	}


	/**  
	* For a specific service, returns a summary across the report interval of activity by Developer.  Sort order of the returned data is number of successful calls for each developer, in descending order.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/developer_activity/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsDeveloperActivityForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/developer_activity/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns a summary across the report interval of activity by Developer.  Sort order of the returned data is number of successful calls for each developer, in descending order.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/developer_activity/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsDeveloperActivityForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/developer_activity/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns a summary across the report interval of activity by Developer.  Sort order of the returned data is number of successful calls for each developer, in descending order.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/developer_activity/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsDeveloperActivityForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/developer_activity/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	
	/**  
	* For a specific service, returns a summary across the report interval of activity by Developer.  Sort order of the returned data is number of successful calls for each developer, in descending order.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/developer_activity/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsDeveloperActivityForServiceForYesterday ( String service_key, String format, int limit)
	{
		String start_date = get1DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/developer_activity/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns a summary across the report interval of activity by Developer.  Sort order of the returned data is number of successful calls for each developer, in descending order.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/developer_activity/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsDeveloperActivityForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/developer_activity/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based counts of errors
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcodesForService ( String service_key, String start_date, String end_date, String format, int errorcode_limit, int limit)
	{
		return callReportingApi("calls/errorcodes/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based counts of errors
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsErrorcodesForService ( String service_key, String start_date, String end_date, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/errorcodes/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based counts of errors
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcodesForService ( String service_key, String format, int errorcode_limit, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcodes/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based counts of errors
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsErrorcodesForService ( String service_key, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcodes/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns error count broken out by Developer
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_developer/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcountByDeveloperForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/errorcount_by_developer/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns error count broken out by Developer
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_developer/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsErrorcountByDeveloperForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/errorcount_by_developer/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns error count broken out by Developer
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_developer/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcountByDeveloperForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcount_by_developer/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns error count broken out by Developer
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_developer/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsErrorcountByDeveloperForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcount_by_developer/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based counts of errors further broken out by Method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcountByMethodForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/errorcount_by_method/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based counts of errors further broken out by Method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsErrorcountByMethodForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/errorcount_by_method/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based counts of errors further broken out by Method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcountByMethodForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcount_by_method/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based counts of errors further broken out by Method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcount_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsErrorcountByMethodForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcount_by_method/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsGeolocationForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/geolocation/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsGeolocationForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/geolocation/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsGeolocationForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/geolocation/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsGeolocationForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/geolocation/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns call latency breaking out Mashery Proxy and the Client API
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/latency/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns call latency breaking out Mashery Proxy and the Client API
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsLatencyForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/latency/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns call latency breaking out Mashery Proxy and the Client API
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns call latency breaking out Mashery Proxy and the Client API
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsLatencyForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based average of call latency further broken out by Method PLUS some other details TBD
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyByMethodForService ( String service_key, String start_date, String end_date, String format, int method_limit, int limit)
	{
		return callReportingApi("calls/latency_by_method/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based average of call latency further broken out by Method PLUS some other details TBD
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsLatencyByMethodForService ( String service_key, String start_date, String end_date, String format, int method_limit, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/latency_by_method/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based average of call latency further broken out by Method PLUS some other details TBD
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyByMethodForService ( String service_key, String format, int method_limit, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency_by_method/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based average of call latency further broken out by Method PLUS some other details TBD
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_method/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsLatencyByMethodForService ( String service_key, String format, int method_limit, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency_by_method/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based average of call latency further broken out by response code  PLUS some other details 
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_responsecode/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyByResponsecodeForService ( String service_key, String start_date, String end_date, String format, int errorcode_limit, int limit)
	{
		return callReportingApi("calls/latency_by_responsecode/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based average of call latency further broken out by response code  PLUS some other details 
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_responsecode/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsLatencyByResponsecodeForService ( String service_key, String start_date, String end_date, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/latency_by_responsecode/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based average of call latency further broken out by response code  PLUS some other details 
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_responsecode/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsLatencyByResponsecodeForService ( String service_key, String format, int errorcode_limit, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency_by_responsecode/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based average of call latency further broken out by response code  PLUS some other details 
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/latency_by_responsecode/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsLatencyByResponsecodeForService ( String service_key, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/latency_by_responsecode/service/:service_key", service_key, null, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based count of calls by method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMethodsForService ( String service_key, String start_date, String end_date, String format, int method_limit, int limit)
	{
		return callReportingApi("calls/methods/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based count of calls by method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsMethodsForService ( String service_key, String start_date, String end_date, String format, int method_limit, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/methods/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based count of calls by method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMethodsForService ( String service_key, String format, int method_limit, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/methods/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based count of calls by method
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param method_limit		int	Limit on the number of distinct methods for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsMethodsForService ( String service_key, String format, int method_limit, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/methods/service/:service_key", service_key, null, start_date, end_date, format, method_limit, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based percentage of successful call responses that were from Mashery Cache
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/responses_from_cache/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsResponsesFromCacheForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/responses_from_cache/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based percentage of successful call responses that were from Mashery Cache
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/responses_from_cache/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsResponsesFromCacheForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/responses_from_cache/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based percentage of successful call responses that were from Mashery Cache
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/responses_from_cache/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsResponsesFromCacheForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/responses_from_cache/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based percentage of successful call responses that were from Mashery Cache
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/responses_from_cache/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsResponsesFromCacheForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/responses_from_cache/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, returns time-based count of calls by status
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsStatusForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/status/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, returns time-based count of calls by status
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsStatusForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/status/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, returns time-based count of calls by status
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsStatusForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/status/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, returns time-based count of calls by status
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsStatusForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/status/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, across the specified interval, returns median call volume by hour for a 24-hour day.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/median_volume_by_hour/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMedianVolumeByHourForService ( String service_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/median_volume_by_hour/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, across the specified interval, returns median call volume by hour for a 24-hour day.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/median_volume_by_hour/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsMedianVolumeByHourForService ( String service_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/median_volume_by_hour/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, across the specified interval, returns median call volume by hour for a 24-hour day.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/median_volume_by_hour/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMedianVolumeByHourForService ( String service_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/median_volume_by_hour/service/:service_key", service_key, null, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, across the specified interval, returns median call volume by hour for a 24-hour day.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/median_volume_by_hour/service/:service_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsMedianVolumeByHourForService ( String service_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/median_volume_by_hour/service/:service_key", service_key, null, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, for a specific developer, call count by error code.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcodesForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int errorcode_limit, int limit)
	{
		return callReportingApi("calls/errorcodes/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, errorcode_limit, limit, null, null);
	}


	/**  
	* For a specific service, for a specific developer, call count by error code.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsErrorcodesForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/errorcodes/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}


	/**  
	* For a specific service, for a specific developer, call count by error code.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsErrorcodesForServiceForDeveloper ( String service_key, String service_dev_key, String format, int errorcode_limit, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcodes/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, errorcode_limit, limit, null, null);
	}
	

	/**  
	* For a specific service, for a specific developer, call count by error code.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/errorcodes/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param errorcode_limit		int	Limit on the number of distinct errorcodes for which data should be returned."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsErrorcodesForServiceForDeveloper ( String service_key, String service_dev_key, String format, int errorcode_limit, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/errorcodes/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, errorcode_limit, limit, apikey, secret);
	}
	

	/**  
	* For a specific service and developer, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsGeolocationForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/geolocation/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service and developer, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsGeolocationForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/geolocation/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service and developer, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsGeolocationForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/geolocation/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service and developer, returns a summary across the report interval of originating geolocation of calls
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/geolocation/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsGeolocationForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/geolocation/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, for a specific developer, call count by method.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMethodsForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/methods/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, for a specific developer, call count by method.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsMethodsForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/methods/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, for a specific developer, call count by method.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsMethodsForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/methods/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, for a specific developer, call count by method.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/methods/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsMethodsForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/methods/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}
	

	/**  
	* For a specific service, for a specific developer, call status.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsStatusForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit)
	{
		return callReportingApi("calls/status/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}


	/**  
	* For a specific service, for a specific developer, call status.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param start_date		String	Oldest date for which data should be retrieved.  Must be less than (earlier) than the end_date."
	* @param end_date		String	Most recent date for which data should be retrieved.  Must be greater (later) than the start date."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret
	*/
	public String callsStatusForServiceForDeveloper ( String service_key, String service_dev_key, String start_date, String end_date, String format, int limit, String apikey, String secret)
	{
		return callReportingApi("calls/status/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}


	/**  
	* For a specific service, for a specific developer, call status.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	*/
	public String callsStatusForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/status/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, null, null);
	}
	

	/**  
	* For a specific service, for a specific developer, call status.
	*
	* URL that will be called in Mashery Reporting API: http://api.mashery.com/v2/rest/:site_id/reports/calls/status/service/:service_key/developer/:service_dev_key
	*
	* @param service_key		String	Mashery API ID for the Service (API) for which you want to get reporting data.  This ID can be found on the API Settings page for the Service in the Mashery Dashboard."
	* @param service_dev_key		String	Developer's Key for the Service (API) for which you want to get reporting data."
	* @param format		String	Desired output format. Valid types are csv and json.  Default type is csv."
	* @param limit		int	Limit on the overall number of items that will be returned."
	* @param apikey  Mashery V2 API key
	* @param secret  Mashery V2 API secret

	*/
	public String callsStatusForServiceForDeveloper ( String service_key, String service_dev_key, String format, int limit, String apikey, String secret)
	{
		String start_date = get7DaysAgo();
    	String end_date = getStartOfDay();
    	
		return callReportingApi("calls/status/service/:service_key/developer/:service_dev_key", service_key, service_dev_key, start_date, end_date, format, limit, apikey, secret);
	}

	
	public String filterOut(String inputLine, int[] filtercols) 
	{
		StringBuffer newBuf = new StringBuffer();
		String[] cells = inputLine.split(",");
		
		for (int i=0; i<cells.length;i++)
		{
			if (Arrays.binarySearch(filtercols, i) > -1)
			{
				continue;
			}
			newBuf.append(cells[i]);
			if (i < cells.length -1)
			{
				newBuf.append(",");
			}
		}
		String returnString = newBuf.toString();
		if (returnString.endsWith(","))
			returnString = returnString.substring(0,returnString.length()-1);
		
		return returnString;
	}
	private String callReportingApi(String resource, String service_key,
			String service_mapi_key, String start_date, String end_date,
			String format, int limit,  String apikey, String secret) {

		resource = updateResourceWithParameterValues(resource, service_key, service_mapi_key);
		try {
    		
    		return getReportDataAsString(resource, format, start_date, end_date, null);
    	} catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
		return null;
	}
	
	
	private String callReportingApi(String resource, String service_key,
			String service_mapi_key, String start_date, String end_date,
			String format, int errorcode_or_method_limit, int limit,  String apikey, String secret) {

		resource = updateResourceWithParameterValues(resource, service_key, service_mapi_key);
		try {
    		return getReportDataAsString(resource, format, start_date, end_date, null);
    	} catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
		return null;
	}


	private String updateResourceWithParameterValues(String resource,
			String service_key, String service_mapi_key) {
		
		if ( service_key != null )
			resource = resource.replace(":service_key", service_key);
		
		if ( service_mapi_key != null )
		resource = resource.replace(":service_dev_key", service_mapi_key);
		
		return resource;
	}


	public String get7DaysAgo()
	{
		 int MILLIS_IN_DAY = 1000 * 60 * 60 * 24 * 7;
		 Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()-MILLIS_IN_DAY));
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);

		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   
		   return returnString;

	}
	public String get1DaysAgo()
	{
		 int MILLIS_IN_DAY = 1000 * 60 * 60 * 24 * 1;
		 Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()-MILLIS_IN_DAY));
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);

		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   
		   return returnString;

	}
	public String getNow() {
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()));
		   
		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";

		   return returnString;
		 }

	public String getStartOfDay() {
		   Calendar dCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		   dCal.setTime(new Date(System.currentTimeMillis()));
		   dCal.set(Calendar.HOUR_OF_DAY, 0);
		   dCal.set(Calendar.MINUTE, 0);
		   dCal.set(Calendar.SECOND, 0);
		   dCal.set(Calendar.MILLISECOND, 0);

		   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   format.setCalendar(dCal);
		   String returnString = format.format(dCal.getTime());
		   
		   returnString = returnString.replaceFirst(" ", "T");
		   returnString = returnString + "Z";
		   return returnString;

		 }
}
