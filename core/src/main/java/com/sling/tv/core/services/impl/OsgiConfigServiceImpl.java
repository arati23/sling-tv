package com.sling.tv.core.services.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

import com.sling.tv.core.services.OsgiConfigService;

@Component(immediate = true, metatype = true, label = "Sling TV : Environment System Settings based on OSGi Configuration", description = " This is a system settings based on OSGi Configuration used for Sling TV Application.")
@Service
public class OsgiConfigServiceImpl implements OsgiConfigService{

	@Property(name = SystemKeys.SLING_ADDRESS_SCRUB_URL, label = "Address Scrub URL", value = StringUtils.EMPTY, description = "Address Scrub End Point URL Used in OTA Component")
	private String slingAddressScrubURL = StringUtils.EMPTY;
	
	@Property(name = SystemKeys.SLING_ANTENNA_SERVICE_URL, label = "Antenna Service URL", value = StringUtils.EMPTY, description = "Antenna Recomendations Titan Service End Point URL Used in OTA Component")
	private String slingAntennaServiceURL = StringUtils.EMPTY;

	@Property(name = SystemKeys.SLING_PC_SERVICE_URL, label = "PC api end point URL", value = StringUtils.EMPTY, description = "Product catalog API URL")
	private String apiURL = StringUtils.EMPTY;

	@Property(name = SystemKeys.SLING_TEALIUM_ENVIRONMENT, label="Tealium URL Environment", value=StringUtils.EMPTY, description="Environment value to load respective tealium utag.sync.js script(ex. dev,qa,beta or prod)")
	private String tealiumEnv = StringUtils.EMPTY;
	
	@Property(name = SystemKeys.SLING_CDN_PURGE_URI_PROTOCOL, label="Protocol of URI Used for CDN Purge", value=StringUtils.EMPTY, description="Protocol entered in the Transport config dialog, for CDN Purge URI")
	private String cdnPurgeURIProtocol = StringUtils.EMPTY;
	
	@Property(name = SystemKeys.SLING_SITE_ROOT_PATH, label="Path of root page", value=StringUtils.EMPTY, description="Path of root page for the purpose of scrubbing the long URL")
	private String rootPagePath = StringUtils.EMPTY;

	private Map<String, Object> props = new ConcurrentHashMap<String, Object>();

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	/**
	 * ServiceLifeCycle method
	 * 
	 * @param props
	 */
	@Activate
	@Modified
	protected void init(final Map<String, Object> props) {
		synchronized (this.props) {
			if (props != null) {
				Set<Entry<String, Object>> entries = props.entrySet();
				for (Entry<String, Object> entry : entries) {
					this.props.put(entry.getKey(), entry.getValue());
				}
				this.slingAddressScrubURL = castToType(props.get(SystemKeys.SLING_ADDRESS_SCRUB_URL), StringUtils.EMPTY);
				this.slingAntennaServiceURL = castToType(props.get(SystemKeys.SLING_ANTENNA_SERVICE_URL), StringUtils.EMPTY);
				this.apiURL = castToType(props.get(SystemKeys.SLING_PC_SERVICE_URL), StringUtils.EMPTY);
				this.tealiumEnv=castToType(props.get(SystemKeys.SLING_TEALIUM_ENVIRONMENT),StringUtils.EMPTY);
				this.cdnPurgeURIProtocol = castToType(props.get(SystemKeys.SLING_CDN_PURGE_URI_PROTOCOL),StringUtils.EMPTY); 
				this.rootPagePath = castToType(props.get(SystemKeys.SLING_SITE_ROOT_PATH),StringUtils.EMPTY);

			}
		}
	}
	
	@Override
	public String getSlingAddressScrubURL() {
		
		return this.slingAddressScrubURL;
	}
	
	@Override
	public String getSlingAntennaServiceURL() {
		
		return this.slingAntennaServiceURL;
	}

	@Override
	public String getSlingPCServiceURL() {

		return this.apiURL;
	}

	@Override
	public String getSlingTealiumEnvironment() {
		return this.tealiumEnv;
	}

	/**
     * 
     * @param obj
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T castToType(final Object obj, final T defaultValue) {
        if (obj != null) {
            if (defaultValue == null) {
                return (T) obj;
            } else {
                if (defaultValue.getClass().isInstance(obj)) {
                    return (T) defaultValue.getClass().cast(obj);
                }
                if (obj instanceof String && defaultValue instanceof Boolean) {
                    return (T) Boolean.valueOf((String) obj);
                }
            }
        }
        return defaultValue;
    }

	@Override
	public String getSlingCDNPurgeURIProtocol() {
		// TODO Auto-generated method stub
		return this.cdnPurgeURIProtocol;
	}
	
	@Override
	public String getSlingRootPagePath() {
		// TODO Auto-generated method stub
		return this.rootPagePath;
	}

}
