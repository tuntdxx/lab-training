package com.lab.vn.labtraining.util;

import org.json.JSONObject;

public class TestFunc {

	public static void main(String[] args) {
		String result = "{'header': '[{\"name\": \"TuanTu\",\"old\":\"3\",\"id\":\"1\"}]','section':'[{\"upper\":100,\"lower\":1]'}";
	    JSONObject json = new JSONObject(result);
	    // json now looks like this :-
	    //
	    if (json != null) {
	        String header = json.getString("header");
	        System.out.println(header);
	    }
	}

}
