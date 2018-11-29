---
title: "Shared vocabularies"
weight: 5
---

# Apio Architect annotations: Vocabulary

This post is a continuation of our last one, where we introduced the new approach for Apio Architect. [Feel free to read it](https://loop.liferay.com/home/-/loop/feed/14068644) if you want to know why on earth we are making this change.

One of the first challenges you had to face when you wanted to create an API using Apio was building the **Representor**. 

For those of you who are not familiar with the term, it’s just the name of a pattern, that consists in decoupling the internal model of the server from the model that you are exposing through the API. This is key if you want your APIs to be as evolvable as possible, because if the internal model changes you can avoid having to change the external model. That way you won't break the compatibility of your API.

The *representor* used to be created using the [Apio Architect DSL](https://gist.github.com/ahdezma/cf63ed10ad60c3d14730b35646a14a06#file-dsl-java-L30-L51). This, while having all the advantages enumerated in the last post (type-safe, compile-time errors, guided development…), turned out to be quite cumbersome to write and it had other problems like:
* Difficult to test.
* Difficult to debug.
* Lambdas everywhere. This functional-programming style was problematic for people not comfortable with Java 8.

With all of this in mind, and having decided that runtime annotations are the way to go, we had to think in a way of abstracting the *representor* creation using annotations.

## New approach

With this annotated-based approach, there is no need to use the DSL anymore. You just have to create a dedicated interface with some annotations. Something like this:

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

## Type

The first thing you have to annotate is the interface itself, using the `@Type` annotation with the name you want to expose.

```java
@Type("BlogPosting")
public interface BlogPosting extends Identifier<Long>
```

## Id

Your interface must have a method that returns the identifier of the resource. You will have to add a `@Id` annotation to that method to indicate it.

```java
@Id
public Long getId()
```

## Field

For each of your fields that you want to return in the responses, you need to include the `@Field` annotation with the name you want to expose.

As you can see you can include arguments like `Locale` and Apio Architect will take them into account that the field is localizable. It will also parse the return type to use it in the API documentation.

```java
@Field("articleBody")
public String getArticleBody(Locale locale)
```

Ok, but what happens if I want to link this resource with others? 

There are other annotations that you'll have to add together with the field annotation, for example, `@LinkedModel`.

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

---

Here you go, this is the **new way of defining a *representor*** in Apio Architect. What do you think? We are open to any kind of feedback :D

> For the full annotation reference you can check its [declaration](https://github.com/liferay/com-liferay-apio-architect/blob/master/apio-architect-api/src/main/java/com/liferay/apio/architect/annotation/Vocabulary.java).