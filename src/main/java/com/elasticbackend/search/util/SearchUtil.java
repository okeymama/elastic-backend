/**
 * 
 */
package com.elasticbackend.search.util;

import org.springframework.util.StringUtils;

/**
 * @author cchaubey
 *
 */
public class SearchUtil {

	public static String setDefaultAsterisk(String strObj){
		if(StringUtils.isEmpty(strObj)) {
			strObj = "*";
		}
		return strObj;
	}
	
}
