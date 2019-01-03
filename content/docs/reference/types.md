---
title: "Types"
order: 2
---

Shared vocabularies are a pillar of evolvable APIs. They avoid coupling external consumers with internal details. Creating an explicit transfer object lets the project internals change without requiring consumers to change.

Creating the transfer object makes these features available:

* Serialization of your entities in all supported formats
* Deserialization for creating/updating actions
* Creation of type documentation: Apio Architect tracks all your types and generates documentation in OpenAPI or Hydra. 

First, you must create an annotated interface that extends the `Identifier` interface parameterized with the type. This tells Apio Architect which type class identifies the resource. In this example, the type is `Long`: 

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

The sections that follow describe each annotation. 

## Type

The `@Type` annotation defines an interface as a type. The annotation's value defines the type's name. For example, the value here is `BlogPosting`:

```
@Type("BlogPosting")
public interface BlogPosting extends Identifier<Long> {

}
```

This annotation also includes a `schemaURL` parameter that lets you configure the schema used. 

## Id

Your interface must have a method annotated with `@Id` that returns the resource's identifier. The method's return type must match the type that you extended `Identifier` with. For example, this `getId` method returns a `Long` and is annotated with `@Id`:

```java
@Id
public Long getId()
```

## Field

Each method that returns a field in the API responses must be annotated with the `@Field` annotation. You must add the `@Field` annotation to all the fields you want to expose/parse. This annotation must also contain the field's name as you want it to appear in the responses. Including a `Locale` argument in these methods makes their fields localizable. Apio Architect also parses the return type for use in the API documentation. 

For example, this `getArticleBody` method is annotated with `@Field("articleBody")` and takes a `Locale` argument:

```java
@Field("articleBody")
public String getArticleBody(Locale locale)
```

The `@Field` annotation contains these additional properties: 

* `schemaURL`: Lets you configure the schema used. 
* `mode`: Lets you set when the field is used. The type interface converts the object to a supported format and parses the body in a request. Some fields, however, only function in one such task. These `mode` property values let you choose when the field is used:
	* `READ_ONLY`: The field is used only for parsing the body.
	* `WRITE_ONLY`: The field is used only for representing the entity.
	* `READ_WRITE`: The field is used in both cases.

Additional annotations, explained in the next sections, can be used together with `@Field` to add more functionality. 

### LinkedModel

To link your resource to another model, add the `@LinkedModel` annotation. This annotation must indicate the class you're linking to. Also, the method must return the ID that identifies that model. For example, this `getCreatorId` method returns the creator's ID. And since the creator is a person, `Person.class` is used for the `@LinkedModel` annotation: 

```java
@Field("creator")
@LinkedModel(Person.class)
public Long getCreatorId()
```

### RelatedCollection

To link your resource to a collection, add the `@RelatedCollection` annotation. This annotation must indicate the class of the resource you're linking to. Also, the method must return the ID that identifies that model. For example, this `getCommentParentId` method returns a comment's parent ID. And since this identifies a comment, `Comment.class` is used for the `@RelatedCollection` annotation:

```java
@Field("comment")
@RelatedCollection(Comment.class)
public Long getCommentParentId()
```

### RelativeURL

If your resource contains relative URLs, adding the `@RelativeURL` annotation to methods that return URLs causes Apio Architect to construct absolute URLs by appending the relative URL to your server's URL (e.g., `/images/11312` will be converted to `http://your-server.com/images/11312`). For example, this `getImageURL` method is annotated with `@RelativeURL`:

```java
@Field("image")
@RelativeURL
public String getImageURL()
```

Note that some URLs of your resource may be relative to the JAX-RS application. For these cases, set the property `fromApplication` to `true` (e.g., `/other-service/11312` will be converted to `http://your-server.com/api/other-service/11312`): 

```java
@Field("image")
@RelativeURL(fromApplication = true)
public String getImageURL()
```
