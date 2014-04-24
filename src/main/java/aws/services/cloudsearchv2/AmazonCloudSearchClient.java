package aws.services.cloudsearchv2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import aws.services.cloudsearchv2.documents.AmazonCloudSearchAddRequest;
import aws.services.cloudsearchv2.documents.AmazonCloudSearchDeleteRequest;
import aws.services.cloudsearchv2.search.AmazonCloudSearchQuery;
import aws.services.cloudsearchv2.search.AmazonCloudSearchResult;
import aws.services.cloudsearchv2.search.Hit;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class AmazonCloudSearchClient extends com.amazonaws.services.cloudsearchv2.AmazonCloudSearchClient {
    private String searchEndpoint;
    private String documentEndpoint;

	/**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2.  A credentials provider chain will be used
     * that searches for credentials in this order:
     * <ul>
     *  <li> Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY </li>
     *  <li> Java System Properties - aws.accessKeyId and aws.secretKey </li>
     *  <li> Instance profile credentials delivered through the Amazon EC2 metadata service </li>
     * </ul>
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @see DefaultAWSCredentialsProviderChain
     */
    public AmazonCloudSearchClient() {
    	super(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2.  A credentials provider chain will be used
     * that searches for credentials in this order:
     * <ul>
     *  <li> Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY </li>
     *  <li> Java System Properties - aws.accessKeyId and aws.secretKey </li>
     *  <li> Instance profile credentials delivered through the Amazon EC2 metadata service </li>
     * </ul>
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonCloudSearchv2
     *                       (ex: proxy settings, retry counts, etc.).
     *
     * @see DefaultAWSCredentialsProviderChain
     */
    public AmazonCloudSearchClient(ClientConfiguration clientConfiguration) {
    	super(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2 using the specified AWS account credentials.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     */
    public AmazonCloudSearchClient(AWSCredentials awsCredentials) {
        super(awsCredentials, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2 using the specified AWS account credentials
     * and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonCloudSearchv2
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonCloudSearchClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(awsCredentials, clientConfiguration);
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2 using the specified AWS account credentials provider.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     */
    public AmazonCloudSearchClient(AWSCredentialsProvider awsCredentialsProvider) {
    	super(awsCredentialsProvider, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2 using the specified AWS account credentials
     * provider and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonCloudSearchv2
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonCloudSearchClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
    	super(awsCredentialsProvider, clientConfiguration);
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonCloudSearchv2 using the specified AWS account credentials
     * provider, client configuration options, and request metric collector.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonCloudSearchv2
     *                       (ex: proxy settings, retry counts, etc.).
     * @param requestMetricCollector optional request metric collector
     */
    public AmazonCloudSearchClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(awsCredentialsProvider, clientConfiguration, requestMetricCollector);
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
	public void addDocument(AmazonCloudSearchAddRequest document) throws AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException, JSONException {
		JSONArray docs = new JSONArray();
		docs.put(toJSON(document));
		updateDocumentRequest(docs.toString());
	}

	/**
	 * An add operation specifies either new documents that you want to add to the index or existing documents that you want to update.
	 * An add operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param documents The documents that need to added or updated
	 * @throws JSONException 
	 * @throws AwsCSMalformedRequestException 
	 * @throws AwsCSInternalServerException 
	 */
	public void addDocuments(List<AmazonCloudSearchAddRequest> documents) throws JSONException, AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException {
		JSONArray docs = new JSONArray();
		for(AmazonCloudSearchAddRequest doc : documents) {
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
	public void deleteDocument(AmazonCloudSearchDeleteRequest document) throws AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException, JSONException {
		JSONArray docs = new JSONArray();
		docs.put(toJSON(document));
		updateDocumentRequest(docs.toString());
	}	

	/**
	 * A delete operation specifies existing documents that you want to delete.
	 * A delete operation is only applied to an existing document if the version number specified in the operation is greater than the existing document's version number.
	 * 
	 * @param document
	 * @throws AwsCSMalformedRequestException
	 * @throws AwsCSInternalServerException
	 * @throws JSONException
	 */
	public void deleteDocuments(List<AmazonCloudSearchDeleteRequest> documents) throws JSONException, AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException {
		JSONArray docs = new JSONArray();
		for(AmazonCloudSearchDeleteRequest doc : documents) {
			docs.put(toJSON(doc));
		}
		updateDocumentRequest(docs.toString());
	}
	
	private void updateDocumentRequest(String body) throws AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException {
		try {
			Response response = Request.Post("https://" + getDocumentEndpoint() + "/2013-01-01/documents/batch")
			        .useExpectContinue()
			        .version(HttpVersion.HTTP_1_1)
			        .addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType())
			        .bodyString(body, ContentType.APPLICATION_JSON)
			        .execute();

			HttpResponse resp = response.returnResponse();
			
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 400 && statusCode < 500) {
				throw new AmazonCloudSearchRequestException(addDocumentErrorMessage(statusCode), body, inputStreamToString(resp.getEntity().getContent()));
			} else if(statusCode >= 500 && statusCode < 600){
				throw new AmazonCloudSearchInternalServerException("Internal Server Error. Please try again as this might be a transient error condition.");
			}
		} catch (ClientProtocolException e) {
			throw new AmazonCloudSearchInternalServerException(e);
		} catch (IOException e) {
			throw new AmazonCloudSearchInternalServerException(e);
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

	private Object toJSON(AmazonCloudSearchDeleteRequest document) throws JSONException {
		JSONObject doc = new JSONObject();
		doc.put("type", "delete");
		doc.put("id", document.id.toLowerCase());
		doc.put("version", document.version);
		return doc;
	}
	
	private JSONObject toJSON(AmazonCloudSearchAddRequest document) throws JSONException {
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

	/**
	 * Get the Search Endpoint set for this Client.
	 * 
	 * @return The Search Endpoint
	 */
	public String getSearchEndpoint() {
		return searchEndpoint;
	}

	/**
	 * Set the Search Endpoint for this Client.
	 * 
	 * @param searchEndpoint The Search Endpoint
	 */
	public void setSearchEndpoint(String searchEndpoint) {
		this.searchEndpoint = searchEndpoint;
	}

	/**
	 * Get the Document Endpoint set for this Client.
	 * 
	 * @return The Document Endpoint
	 */
	public String getDocumentEndpoint() {
		return documentEndpoint;
	}

	/**
	 * Set the Document Endpoint for this Client.
	 * 
	 * @param documentEndpoint The Document Endpoint
	 */
	public void setDocumentEndpoint(String documentEndpoint) {
		this.documentEndpoint = documentEndpoint;
	}	
	
	/**
	 * Execute a search and return result.
	 * 
	 * @param query search query to be executed.
	 * @return result of the search.
	 * @throws AmazonCloudSearchRequestException 
	 * @throws IllegalStateException 
	 * @throws AmazonCloudSearchInternalServerException 
	 * @throws JSONException 
	 */
	public AmazonCloudSearchResult search(AmazonCloudSearchQuery query) throws IllegalStateException, AmazonCloudSearchRequestException, AmazonCloudSearchInternalServerException, JSONException {
		AmazonCloudSearchResult result = null;
		
		try {
			Response response = Request.Get("https://" + getSearchEndpoint() + "/2013-01-01/search?" + query.build())
			        .useExpectContinue()
			        .version(HttpVersion.HTTP_1_1)
			        .addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType())
			        .execute();

			HttpResponse resp = response.returnResponse();
			String responseBody = inputStreamToString(resp.getEntity().getContent());
			
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 400 && statusCode < 500) {
				throw new AmazonCloudSearchRequestException(addDocumentErrorMessage(statusCode), "", responseBody);
			} else if(statusCode >= 500 && statusCode < 600){
				throw new AmazonCloudSearchInternalServerException("Internal Server Error. Please try again as this might be a transient error condition.");
			}
			
			result = fromJSON(responseBody);
			
		} catch (ClientProtocolException e) {
			throw new AmazonCloudSearchInternalServerException(e);
		} catch (IOException e) {
			throw new AmazonCloudSearchInternalServerException(e);
		}
		
		return result;
	}

	private AmazonCloudSearchResult fromJSON(String responseBody) throws JSONException {
		AmazonCloudSearchResult result = new AmazonCloudSearchResult();

		JSONObject root = new JSONObject(responseBody);
		JSONObject status = root.getJSONObject("status");
		if(status != null) {
			result.rid = status.getString("rid");
			result.time = status.getLong("time-ms");
		}
		
		JSONObject hits = root.getJSONObject("hits");
		if(hits != null) {
			result.found = hits.getInt("found");
			result.start = hits.getInt("start");
			if(result.found > 0) {
				JSONArray hitArray = hits.getJSONArray("hit");
				if(hitArray != null) {
					for(int i = 0; i < hitArray.length(); i++) {
						JSONObject row = hitArray.getJSONObject(i);
						Hit hit = new Hit();
						hit.id = row.getString("id");
						JSONObject fields = row.getJSONObject("fields");
						String[] names = JSONObject.getNames(fields);
						for(String name : names) {
							if(hit.fields == null) {
								hit.fields = new HashMap<String, String>();
							}
							hit.fields.put(name, fields.getString(name));
						}
						if(result.hits == null) {
							result.hits = new ArrayList<Hit>();
						}
						result.hits.add(hit);
					}
				}
			}
		}
		
		return result;
	}
}
