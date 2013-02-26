package com.bryght.services.cloudsearch;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * AmazonCloudSearch client for uploading, updating and deleting Documents.  All service calls made
 * using this client are blocking, and will not return until the service call completes.
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class AwsCSDocumentService {
	private String documentEndPoint;
	
	public AwsCSDocumentService(String documentEndPoint) {
		this.documentEndPoint = documentEndPoint;
	}
	
	/**
	 * An add operation specifies either a new document that you want to add to the index or an existing document that you want to update.
	 * An add operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param document The document that need to added or updated
	 * @throws AwsCSMalformedRequestException 
	 * @throws AwsCSInternalServerException 
	 * @throws JSONException 
	 */
	public void addDocument(AwsCSAddRequest document) throws AwsCSMalformedRequestException, AwsCSInternalServerException, JSONException {
		JSONArray docs = new JSONArray();
		docs.put(toJSON(document));
		updateDocumentRequest(docs.toString());
	}

	/**
	 * An add operation specifies either a new document that you want to add to the index or an existing document that you want to update.
	 * An add operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param documents The documents that need to added or updated
	 * @throws JSONException 
	 * @throws AwsCSMalformedRequestException 
	 * @throws AwsCSInternalServerException 
	 */
	public void addDocuments(List<AwsCSAddRequest> documents) throws JSONException, AwsCSMalformedRequestException, AwsCSInternalServerException {
		JSONArray docs = new JSONArray();
		for(AwsCSAddRequest doc : documents) {
			docs.put(toJSON(doc));
		}
		
		updateDocumentRequest(docs.toString());
	}

	
	/**
	 * A delete operation specifies an existing document that you want to delete.
	 * A delete operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param document
	 * @throws AwsCSMalformedRequestException
	 * @throws AwsCSInternalServerException
	 * @throws JSONException
	 */
	public void deleteDocument(AwsCSDeleteRequest document) throws AwsCSMalformedRequestException, AwsCSInternalServerException, JSONException {
		JSONArray docs = new JSONArray();
		docs.put(toJSON(document));
		updateDocumentRequest(docs.toString());
	}	

	/**
	 * A delete operation specifies an existing documents that you want to delete.
	 * A delete operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param document
	 * @throws AwsCSMalformedRequestException
	 * @throws AwsCSInternalServerException
	 * @throws JSONException
	 */
	public void deleteDocuments(List<AwsCSDeleteRequest> documents) throws JSONException, AwsCSMalformedRequestException, AwsCSInternalServerException {
		JSONArray docs = new JSONArray();
		for(AwsCSDeleteRequest doc : documents) {
			docs.put(toJSON(doc));
		}
		
		updateDocumentRequest(docs.toString());
	}
	
	private void updateDocumentRequest(String body)throws AwsCSMalformedRequestException, AwsCSInternalServerException {
		try {
			Response response = Request.Post("https://" + documentEndPoint + "/2011-02-01/documents/batch")
			        .useExpectContinue()
			        .version(HttpVersion.HTTP_1_1)
			        .addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType())
			        .bodyString(body, ContentType.APPLICATION_JSON)
			        .execute();

			HttpResponse resp = response.returnResponse();
			
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 400 && statusCode < 500) {
				throw new AwsCSMalformedRequestException(addDocumentErrorMessage(statusCode), body, inputStreamToString(resp.getEntity().getContent()));
			} else if(statusCode >= 500 && statusCode < 600){
				throw new AwsCSInternalServerException("Internal Server Error. Please try again as this might be a transient error condition.");
			}
		} catch (ClientProtocolException e) {
			throw new AwsCSInternalServerException(e);
		} catch (IOException e) {
			throw new AwsCSInternalServerException(e);
		}
	}
	
	private String inputStreamToString(InputStream in) throws IOException {
		StringWriter output = new StringWriter();
		InputStreamReader input = new InputStreamReader(in);
		char[] buffer = new char[1024 * 4];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toString();
	}

	private String addDocumentErrorMessage(int statusCode) {
		if(statusCode == 400) {
			return "The Content-Type header is missing.	";
		} else if(statusCode == 411) {
			return "The Content-Length header is missing.";
		} else if(statusCode == 404) {
			return "URL path does not match ''/YYYY-MM-DD/documents/batch''.";
		} else if(statusCode == 405) {
			return "The HTTP method is not POST. Requests must be posted to documents/batch.";
		} else if(statusCode == 406) {
			return "Accept header specifies a content type other than 'application/xml' or 'application/json'. Responses can be sent only as XML or JSON.";
		} else if(statusCode == 413) {
			return "The length of the request body is larger than the maximum allowed value.";
		} else if(statusCode == 415) {
			return "The content type is something other than 'application/json' or 'application/xml' or The character set is something other than 'ASCII', 'ISO-8859-1', or 'UTF-8'";
		}  else if(statusCode == 403) {
			return "Access Denied";
		}else{
			return "Malformed request.";
		}
	}

	private Object toJSON(AwsCSDeleteRequest document) throws JSONException {
		JSONObject doc = new JSONObject();
		doc.put("type", "add");
		doc.put("id", document.id);
		doc.put("version", document.version);
		return doc;
	}

	
	private JSONObject toJSON(AwsCSAddRequest document) throws JSONException {
		JSONObject doc = new JSONObject();
		doc.put("type", "add");
		doc.put("id", document.id.toLowerCase());
		doc.put("version", document.version);
		doc.put("lang", document.lang);
		
		JSONObject fields = new JSONObject();
		for(Map.Entry<String, Object> entry : document.fields.entrySet()) {
			if(entry.getValue() instanceof Collection) {
				JSONArray array = new JSONArray();
				Iterator i = ((Collection)entry.getValue()).iterator();
				while(i.hasNext()) {
					array.put(i.next());
				}
				fields.put(entry.getKey(), array);
			} else {
				fields.put(entry.getKey(), entry.getValue());
			}
		}
		doc.put("fields", fields);
		return doc;
	}
	
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
