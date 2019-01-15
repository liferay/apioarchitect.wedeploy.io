---
title: "Retrieve"
order: 1
---

The `@Retrieve` annotation represents a `GET` request to the resource's URI. Annotating a method with `@Retrieve` instructs Apio Architect to use that method to get data. You can use this annotation to declare actions that get individual elements and collections.

## Using @Retrieve to Get a Root Collection

A root collection is a first-level collection that can be retrieved without specifying any individual resource. To specify a method for retrieving such a collection, annotate it with `@Retrieve` in the Router for the resource type. 

> Note that this action can support pagination. For information on this, see the [pagination documentation](/docs/introduction/features/collections-pagination.html).

For example, here's a Router for the `BlogPosting` type that contains a `retrieveBlogPostings` method annotated with `@Retrieve`:

```java
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Retrieve
    List<BlogPosting> retrieveBlogPostings();
    
}
```

Sending a `GET` request to `http://server_url/api/blog-posting` causes Apio Architect to invoke this method and return the blog posting list. 

## Using @Retrieve to Get a Single Element

To tell Apio Architect that an action returns a single element, add an element ID parameter and annotate it with `@Id`. For example, this `retrieveBlogPostingWithId` method contains an `id` parameter annotated with `@Id` for the blog posting's ID. This method therefore returns the `BlogPosting` that matches the ID: 

```java
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Retrieve
    BlogPosting retrieveBlogPostingWithId(@Id long id);
    
}
```

Apio Architect invokes this `retrieveBlogPostingWithId` method for any `GET` request to `http://server_url/api/blog-posting/{id}`, where `{id}` in the URI is the ID of the blog posting to retrieve. 

## Using @Retrieve to Get a Child Collection

A common task when using APIs is to get information associated with a parent resource. For example, you may wish to let your API's consumers get all the blog postings that belong to a person. The typical URL for this use case is `person/{id}/blog-posting`, where `{id}` is the person's ID.  To create such an action, you must add a parameter for the parent resource's ID and annotate it with `@ParentId(YourType.class)`. 

> Note that this action can support pagination. For information on this, see the [pagination documentation](/docs/introduction/features/collections-pagination.html).

For example, this `retrieveBlogPostingsForPersonWithId` method has an `id` parameter annotated with `@ParentId(Person.class)`. This method therefore returns the blog postings of the person matching the ID: 

```java
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.ParentId;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Retrieve
    List<BlogPosting> retrieveBlogPostingsForPersonWithId(@ParentId(Person.class) String id);
    
}
```

Apio Architect invokes this `retrieveBlogPostingsForPersonWithId` method for any `GET` request to `http://server_url/api/person/{id}/blog-posting`, where `{id}` in the URI is the person's ID. 
