---
title: "Create"
order: 2
---

Represents a `POST` request on the resource's URI to create a new element. 

A method annotated with `@Create` will instruct Apio Architect to declare an action used to create or add a new element to a colle ction, depending on the type of resource:

### Using `@Create` to create a new element

If the action should create a new resource, the method annotated with `@Create` should have a parameter of the same type as our resource, annotated with `@Body` (this instructs Apio Architect that this value must be retrieved from the request's body).

> To create the parameter annotated with `@Body` Apio Architect will use the information obtained from the `BlogPosting`'s annotations. See [`@Field#mode`](/docs/reference/types.html#mode) for more information.

```java
import com.liferay.apio.architect.annotation.Actions.Create;
import com.liferay.apio.architect.annotation.Body;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Create
    BlogPosting createBlogPosting(@Body BlogPosting blogPosting) {
        // Code that persists the new BlogPosting
    }
    
}
```

With that action defined, Apio Architect will map the method invocation to any `POST` to  `http://server_url/api/blog-posting` and will parse the body to retrieve the `BlogPosting` and provide it as the argument.

### Using `@Create` to create a new element depending on a "parent" resource

When the resource to be created is a nested resource (has a "parent"), such as adding a `BlogPosting` inside a `Category`, represented behind the URI `category/{categoryId}/blog-posting`, the parent resource's id (the `categoryId`, in the example) should be referenced.

The `@ParentId` annotation on a method arguments instruct Apio Architect to parse the URL and fill the argument with the corresponding id. The annotation must be configured with the parent resource's type.

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
        @ParentId(Category.class) String id, @Body BlogPosting blogPosting) {
             /* Retrieve Category using category Id, 
                and persist new blogPosting linked to Category Resurce */
        }
    
}
```

Apio Architect will invoke this method whenever a `POST` request is received at  `http://server_url/api/category/{id}/blog-posting`, filling a the id parameter with the categoryIds from the URI and the new `BlogPosting` parsing the request body.