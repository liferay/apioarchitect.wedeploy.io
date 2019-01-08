---
title: "Create"
order: 2
---

Performed by executing a `POST` request on the resource's URI. 

When a method is annotated with `@Create` the library will use it to create data. This annotation can be used to declare actions that create and add elements to all types of collections. Let's see the use depending on each case:

### Using `@Create` to create a new element

We can create an action to create new elements by creating a method with a parameter of the same type as our resource, annotated with `@Body` (this tells the library that this value must be recovered from the request's body).

> To create the parameter annotated with `@Body` Apio Architect will use the information obtained from the `BlogPosting`'s annotations. See [`@Field#mode`](/docs/reference/types.html#mode) for more information.

```java
import com.liferay.apio.architect.annotation.Actions.Create;
import com.liferay.apio.architect.annotation.Body;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Create
    BlogPosting createBlogPosting(@Body BlogPosting blogPosting);
    
}
```

Now executing a `POST` request containing a `BlogPosting` as body to `http://server_url/api/blog-posting` will create a new `BlogPosting`, add it to the list and return it.

### Using `@Create` to create a new element depending on a "parent" resource

We can have the case where in order to create our element, we need information about a "parent" resource. For example, if we want to add a blog posting to a certain category, the typical URL for this case would be `category/{id}/blog-posting` so we can obtain the category `ID` information. In order to create such action we need to add a parameter of the same type as our parent's `ID` (`Category`'s `ID` in our example) and annotate it with `@ParentId`, providing the parent's type to the annotation. Also, we must add a parameter of the same type as our resource, annotated with `@Body` (this tells the library that this value must be recovered from the request's body).

> To create the parameter annotated with `@Body` Apio Architect will use the information obtained from the `BlogPosting`'s annotations. See [`@Field#mode`](/docs/reference/types.html#mode) for more information.

```java
import com.liferay.apio.architect.annotation.Actions.Create;
import com.liferay.apio.architect.annotation.Body;
import com.liferay.apio.architect.annotation.ParentId;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Create
    BlogPosting createBlogPostingForCategoryWithId(
        @ParentId(Category.class) String id, @Body BlogPosting blogPosting);
    
}
```

Now executing a `POST` request containing a `BlogPosting` as body to `http://server_url/api/category/{id}/blog-posting` will create a new `BlogPosting` for the provided `Category`'s `id`, add it to the list and return it.