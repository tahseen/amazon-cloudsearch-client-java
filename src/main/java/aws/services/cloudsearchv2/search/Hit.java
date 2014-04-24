package aws.services.cloudsearchv2.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;

public class Hit {
	public String id;
	public Map<String, String> fields = new HashMap<String, String>();
	
	public Integer getIntegerField(String field) {
		Integer rlt = null;
		if(fields.containsKey(field)) {
			rlt = Integer.parseInt(fields.get(field));
		}
		return rlt;
	}	
	
	public Long getLongField(String field) {
		Long rlt = null;
		if(fields.containsKey(field)) {
			rlt = Long.parseLong(fields.get(field));
		}
		return rlt;
	}	
	
	public Double getDoubleField(String field) {
		Double rlt = null;
		if(fields.containsKey(field)) {
			rlt = Double.parseDouble(fields.get(field));
		}
		return rlt;
	}	
	
	public String getField(String field) {
		return fields.get(field);
	}	
	
	public List<String> getListField(String field) throws JSONException {
		List<String> rlt = null;
		
		if(fields.containsKey(field)) {
			String value = fields.get(field);
			JSONArray array = new JSONArray(value);
			if(array.length() > 0) {
				rlt = new ArrayList<String>();
				for(int i = 0; i < array.length(); i++) {
					rlt.add(array.getString(i));
				}
			}
		}
		
		return rlt;
	}
}
