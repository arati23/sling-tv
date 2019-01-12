/**
 * 
 */
package com.sling.tv.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sling.tv.core.services.ReadOSGIConfigValues;

/**
 * @author Dileep Muraleedharan
 *
 */
@Component(immediate = true, metatype = true, label = "Sling TV Osgi Configuration Values", description = "Sling TV OSGI Configuration")
@Service
public class ReadOSGIConfigValuesImpl implements ReadOSGIConfigValues {

	private static final Logger LOG = LoggerFactory.getLogger(ReadOSGIConfigValuesImpl.class);

	@Property(unbounded = PropertyUnbounded.ARRAY, label = "Multi String", cardinality = 50, description = "Osgi Config Values")
	private static final String MULTI_FIELD = "slingmultifield";

	private String[] multiString;

	Map<String, String> slingOsgiMap = new HashMap<String, String>();

	@Activate
	protected void activate(Map<String, Object> properties) {
		LOG.info("Sling  AEM ConfigurationService : activating configuration service");
		readProperties(properties);
	}

	protected void readProperties(Map<String, Object> properties) {
		this.multiString = PropertiesUtil.toStringArray(properties.get("slingmultifield"));
		if (multiString != null) {
			for (String eachVal : multiString) {

				if (StringUtils.isNotBlank(eachVal) && eachVal.contains("=")) {
					String arrValues[] = eachVal.split("=");
					slingOsgiMap.put(arrValues[0], arrValues[1]);

				}

			}

		}

	}

	@Override
	public String getOsgiConfigValue(String key) {

		if (slingOsgiMap.containsKey(key)) {
			return slingOsgiMap.get(key);
		} else {
			return null;
		}

	}

}
