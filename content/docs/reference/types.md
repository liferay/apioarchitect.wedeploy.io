---
title: "Types"
weight: 5
---

One of the pillars of the evolvable APIs we are proposing is the shared vocabularies. You can read more detailed information [here](). The principal reason to worry about this is to avoid coupling our external consumers with our internal details. Having to create a explicit transfer object allow us to change the internal part of our project without having to change its consumers.

After creating our transfer object, you'll obtain a list of features out of the box, like:

 * Serialization of your entities in all [supported formats]()
 * Deserialization for creating/updating actions
 * Types documentation, APIO will track all your types and it will generate documentation in the form of [OpenAPI profile]() or [Hydra profile]()
 
 
We have to create an annotated interface, something like this:

```java
@Type("BlogPosting")
public interface BlogPosting extends Identifier<Long> {

	@Field("alternativeHeadline")
	public String getAlternativeHeadline();

	@Field("articleBody")
	public String getArticleBody();

	@Field("creator")
	@LinkedModel(Person.class)
	public Long getCreatorId();

	@Field("dateCreated")
	public Date getDateCreated();

	@Field("dateModified")
	public Date getDateModified();

	@Field("fileFormat")
	public String getFileFormat();

	@Field("headline")
	public String getHeadline();

	@Id
	public Long getId();

	@Field("review")
	public List<Review> getReviews();

}
```

Now let’s see each annotation in detail. 


## Identifier

When defining your interface for your type, you have to extend from the `Identifier` interface. This is the way to tell Apio what is the class that identifies that resource, in this example the type `Long`. 

```
public interface BlogPosting extends Identifier<Long> {

}
```

## Type

The `Type` annotation is used to define an interface as a type, it has a property value where you have to define the name of the type:

```
@Type("BlogPosting")
public interface BlogPosting extends Identifier<Long> {

}
```

It also includes a `schemaURL` parameter that let you configure the schema used. You can read more about schemas [here]().

## Id

Your interface must have a method that returns the identifier of the resource. You will have to add a `@Id` annotation to that method to indicate it.

```java
@Id
public Long getId()
```

**Note: the return type of the annotated method should match the type defined when extending `Identifier`**

## Field

For each of your fields that you want to return in the responses, you need to include the `@Field` annotation with the name you want to expose.

As you can see you can include arguments like `Locale` and Apio will take them into account that the field is localizable. It will also parse the return type to use it in the API documentation.

```java
@Field("articleBody")
public String getArticleBody(Locale locale)
```

Additional properties: 

* `schemaURL`: let you configure the schema used. You can learn more about schemas [here]().
* `mode`: the type interface is used for converting the object to a supported format and for parsing the body in a request. Some fields are only relevant in one of this tasks. Using `mode` property allow you to choose when the property is used:
	* `READ_ONLY`: it will only be used for parsing the body.
	* `WRITE_ONLY`: it will only be used when representing the entity.
	* `READ_WRITE`: it will be used in both cases.

	
You have to add this `Field` annotations to all the fields you want to expose/parse, but there is more to come. A set of annotations that needs to be added together with `Field` can be used to add more functionalities.


### LinkedModel

If you want your resource to be linked to another model, you'll have to add the `@LinkedModel` annotation. The annotation must indicate which class is the class you are linking to, and the method should return the `id` that identifies that model.

```java
@Field("creator")
@LinkedModel(Person.class)
public Long getCreatorId()
```

### RelatedCollection

The same as with `@LinkedModel`, if you want to add a link to a collection, you will have to add the `@RelatedCollection` annotation, including the class of the resource you want to link.

```java
@Field("comment")
@RelatedCollection(Comment.class)
public Long getCommentParentId()
```

### RelativeURL

If your resource has associated relative URLs, you can add this annotation and Apio will append the URL to the URL of your server. e.g. `/images/11312` will be converted to `http://your-server.com/images/11312`

```java
@Field("image")
@RelativeURL
public String getImageURL()
```

Some URLs of your resource may be relative to the JAX-RS Application, for this cases you can set the property `fromApplication` to true.

```java
@Field("image")
@RelativeURL(fromApplication = true)
public String getImageURL()
```

e.g. `/other-service/11312` will be converted to `http://your-server.com/api/other-service/11312`
