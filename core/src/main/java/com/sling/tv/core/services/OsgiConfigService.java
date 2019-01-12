package com.sling.tv.core.services;

public interface OsgiConfigService {
	public interface SystemKeys {
		String SLING_ADDRESS_SCRUB_URL = "slingAddressScrubURL";
		String SLING_ANTENNA_SERVICE_URL = "slingAntennaServiceURL";
		String SLING_PC_SERVICE_URL = "slingPCServiceURL";
		String SLING_TEALIUM_ENVIRONMENT="slingTealiumEnvironment";
		String SLING_CDN_PURGE_URI_PROTOCOL="slingCDNPurgeURIProtocol";
		String SLING_SITE_ROOT_PATH = "slingRootPagePath";
		
	}

	public String getSlingAddressScrubURL();
	public String getSlingAntennaServiceURL();
	public String getSlingPCServiceURL();
	public String getSlingTealiumEnvironment();
	public String getSlingCDNPurgeURIProtocol();
	String getSlingRootPagePath();

}
