package com.uipro.vaadinapps.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import com.uipro.vaadinapps.samples.backend.data.Category;
import com.uipro.vaadinapps.samples.backend.data.Product;
import com.uipro.vaadinapps.samples.backend.mock.UiproRequestDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable {

    public static DataService get() {
        return UiproRequestDataService.getInstance();
    }
    
    public static void set(int uid, boolean isNewPage, String template, String element, String elementType,
			String elementName, String elementId, String elementPosition, String elementColor, String elementValue) {
        UiproRequestDataService.setInstance(uid, isNewPage, template, element, elementType, elementName, elementId, elementPosition, elementColor, elementValue);
    }

}
