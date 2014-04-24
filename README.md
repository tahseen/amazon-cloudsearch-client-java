Amazon Cloudsearch Client Java
==============================

Amazon CloudSearch Client for Document Service and Searching. Amazon SDK does not provide java client for document indexing and seacrching. This simple library is created to fill that gap.

Instantiate Client
==================
aws.services.cloudsearchv2.AmazonCloudSearchClient extends the official com.amazonaws.services.cloudsearchv2.AmazonCloudSearchClient client. So all the official funtionality is avaible with this client as well.
```java
AWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);;
AmazonCloudSearchClient client = new AmazonCloudSearchClient(awsCredentials);
client.setSearchDomain(searchEndpoint);
client.setDocumentDomain(documentEndpoint);
```

Adding/Updating Documents
=========================
Adding single document:

```java
AmazonCloudSearchAddRequest addRequest = new AmazonCloudSearchAddRequest();
addRequest.id = "acb123";
addRequest.version = 1;
addRequest.addField("text_field", "Some Value");
addRequest.addField("int_field", 123);

documentService.addDocument(addRequest);
```

More efficent to add multiple documents in a batch:
```java
AmazonCloudSearchAddRequest addRequest1 = new AmazonCloudSearchAddRequest();
addRequest1.id = "acb123";
addRequest1.version = 1;
addRequest1.addField("text_field", "Some Value");
addRequest1.addField("int_field", 123);

AmazonCloudSearchAddRequest addRequest2 = new AmazonCloudSearchAddRequest();
addRequest2.id = "xyz123";
addRequest2.version = 1;
addRequest2.addField("text_field", "Some Value");
addRequest2.addField("int_field", 123);

List<AmazonCloudSearchAddRequest> addRequests = new ArrayList<>();

addRequests.add(addRequest1);
addRequests.add(addRequest2);

documentService.addDocument(addRequests);
```

Deleting Documents
==================

```java
AmazonCloudSearchAddRequest deleteRequest = new AwsCSDeleteRequest();
deleteRequest.id = "acb123";
deleteRequest.version = 1;

documentService.deleteDocument(deleteRequest);
```

Again more efficent to delete multiple documents in a batch:
```java
AmazonCloudSearchDeleteRequest deleteRequest1 = new AmazonCloudSearchDeleteRequest();
deleteRequest1.id = "acb123";
deleteRequest1.version = 1;

AmazonCloudSearchDeleteRequest deleteRequest2 = new AmazonCloudSearchDeleteRequest();
deleteRequest2.id = "acb223";
deleteRequest2.version = 1;

List<AmazonCloudSearchDeleteRequest> deleteRequests = new ArrayList<>();

deleteRequests.add(deleteRequest1);
deleteRequests.add(deleteRequest1);

documentService.deleteDocument(deleteRequests);
```

Search Documents
=================

For complete docuemntation of all the fields and options see:

http://docs.aws.amazon.com/cloudsearch/latest/developerguide/search-api.html

Below is a simple example of searching documents.
```java
AmazonCloudSearchQuery query = new AmazonCloudSearchQuery();
query.query = "Dining Tables";
query.returnFields = "title,sku_no,description";
query.start = 20;
query.size = 20;
query.addExpression("sort_expr","(0.3*popularity)+(0.7*_score)");
query.sort("sort_expr", "desc");

AmazonCloudSearchResult result = client.search(query);
```

Dependencies 
============
* Amazon SDK 1.7.6 and higher
* Apache Fluent HC Module 4.2 and higher

Maven
=====

```xml
<dependency>      
  <groupId>com.amazonaws</groupId>
  <artifactId>aws-java-sdk</artifactId>
  <version>1.7.6</version>
</dependency>    
<dependency>      
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>fluent-hc</artifactId>
  <version>4.2</version>
</dependency>
```
