package com.uipro.vaadinapps.samples.backend.mock;

import com.uipro.vaadinapps.samples.backend.DataService;
import com.uipro.vaadinapps.samples.backend.data.UiproRequest;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class UiproRequestDataService extends DataService {

    private static UiproRequestDataService INSTANCE;
    private UiproRequest requestData;

    private UiproRequestDataService(int uid, boolean isNewPage, String template, String element, String elementType,
			String elementName, String elementId, String elementPosition, String elementColor, String elementValue) {
    	//Setting request data into object
    	setRequestData(new UiproRequest(uid, isNewPage, template, element, elementType, elementName, elementId, elementPosition, elementColor, elementValue));
    }

    public synchronized UiproRequest getRequestData() {
		return requestData;
	}

	public synchronized void setRequestData(UiproRequest requestData) {
		this.requestData = requestData;
	}

	public synchronized static DataService getInstance() {
        return INSTANCE;
    }
	
	public synchronized static void setInstance(int uid, boolean isNewPage, String template, String element, String elementType,
			String elementName, String elementId, String elementPosition, String elementColor, String elementValue) {
         INSTANCE = new UiproRequestDataService(uid, isNewPage, template, element, elementType, elementName, elementId, elementPosition, elementColor, elementValue);
    }

}
