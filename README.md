Amazon Cloudsearch Client Java
==============================

Amazon CloudSearch Client for Document Index Service. Amazon SDK does not provide java api for document indexing. This simple Api is created to fill that gap.


Adding/Updating Documents
=========================
Adding single document:

```java
AwsCSAddRequest addRequest = new AwsCSAddRequest();
addRequest.id = "acb123";
addRequest.version = 1;
addRequest.addField("text_field", "Some Value");
addRequest.addField("int_field", 123);

documentService.addDocument(addRequest);
```

More efficent to add multiple documents in a batch:
```java
AwsCSAddRequest addRequest1 = new AwsCSAddRequest();
addRequest1.id = "acb123";
addRequest1.version = 1;
addRequest1.addField("text_field", "Some Value");
addRequest1.addField("int_field", 123);

AwsCSAddRequest addRequest2 = new AwsCSAddRequest();
addRequest2.id = "xyz123";
addRequest2.version = 1;
addRequest2.addField("text_field", "Some Value");
addRequest2.addField("int_field", 123);

List<AwsCSAddRequest> addRequests = new ArrayList<>();

addRequests.add(addRequest1);
addRequests.add(addRequest2);

documentService.addDocument(addRequests);
```

Deleting Documents
==================

```java
AwsCSDeleteRequest deleteRequest = new AwsCSDeleteRequest();
deleteRequest.id = "acb123";
deleteRequest.version = 1;

documentService.deleteDocument(deleteRequest);
```

Dependencies 
============
* Amazon SDK 1.3.32 and higer
* Apache HTTP Client 4.2.3 and higher
* Apache Fluent HC Module 4.2.3 and higher


Maven
=====

```xml
<dependency>
  <groupId>com.amazonaws</groupId>
	<artifactId>aws-java-sdk</artifactId>
	<version>1.3.32</version>
</dependency>
<dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>httpclient</artifactId>
	<version>4.2.3</version>
</dependency>
<dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>fluent-hc</artifactId>
	<version>4.2.3</version>
</dependency>
```
