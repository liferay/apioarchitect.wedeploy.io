---
title: "Retrieve"
order: 1
---

The most typical action that can be done on a resource. Performed by executing a `GET` request on the resource's URI. 

When a method is annotated with `@Retrieve` the library is told that that method is in charge of "getting" data. This annotation can be used to declare actions that obtain both individual elements and all types of collections. Let's see the use depending on each case:

### Using `@Retrieve` to get a "root" collection

When we have a first-level collection (those that do not depend on any resource to be obtained) we can create an action to obtain it by doing the following:

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

Now calling `http://server_url/api/blog-posting` will return the blog posting list. 

> This action can support `Pagination`, check [this page](/docs/introduction/features/collections-pagination.html) if you want to see how.

### Using `@Retrieve` to get a single element

To tell Apio Architect that this action returns a single `BlogPosting` we just need to add a parameter of the same type as our `BlogPosting`'s `ID` and annotate it with `@Id`.

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

Now calling `http://server_url/api/blog-posting/{id}` will return the blog posting with the provided `id`. 

### Using `@Retrieve` to get a "child" collection

We can have the case where in order to calculate our list, we need information about a "parent" resource. For example, if we want to obtain all the blog postings of a person, the typical URL for this case would be `person/{id}/blog-posting` so we can obtain the person `ID` information. In order to create such action we just need to add a parameter of the same type as our parent's `ID` (`Person`'s `ID` in our example) and annotate it with `@ParentId`, providing the parent's type to the annotation:

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

Now calling `http://server_url/api/person/{id}/blog-posting` will return the blog postings of the person with the provided `id`. 

> This action can support `Pagination`, check [this page](/docs/introduction/features/collections-pagination.html) if you want to see how.