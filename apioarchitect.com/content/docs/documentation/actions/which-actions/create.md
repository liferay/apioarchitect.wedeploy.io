---
title: "Create"
order: 2
---

The `@Create` annotation represents a `POST` request to the resource's URI to create a new element. Annotating a method with `@Create` instructs Apio Architect to declare an action that creates or adds a new element to a collection, depending on the resource type. 

## Using @Create to Create a New Element

If the action creates a new resource, the method annotated with `@Create` must have a parameter of the same type as that resource. You must also annotate that parameter with `@Body`. This instructs Apio Architect to retrieve the parameter value from the request's body, using the `mode` property of the resource type's `@Field` annotation. For more information, see the Field section in the [Types documentation](/docs/documentation/types.html).

For example, here's a Router for the `BlogPosting` type that contains a `createBlogPosting` method annotated with `@Create`. This method contains a `BlogPosting` parameter annotated with `@Body`: 

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

Apio Architect maps the `createBlogPosting` method to `POST` requests sent to `http://server_url/api/blog-posting`. The body is parsed to retrieve the `BlogPosting` and provide it as the method's argument. 

## Using @Create to Create a New Element Depending on a Parent Resource

When creating a nested resource (e.g., a resource with a parent resource), you must reference the parent resource's ID. For example, to create a `BlogPosting` in a `Category`, you must make a `POST` request to `category/{categoryId}/blog-posting`, replacing `{categoryId}` with the `Category`'s ID. 

For this to work, you must use the `@ParentId` annotation on an ID parameter in the method that creates the nested resource. This annotation instructs Apio Architect to parse the URL and fill the argument with the corresponding ID. You must configure the annotation with the parent resource's type. 

For example, the `id` parameter for this method is annotated with `@ParentId(Category.class)`. This instructs Apio Architect to create a new resource (`BlogPosting`) in the `Category` that matches `id`:

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
                and persist new blogPosting linked to Category Resource */
        }
    
}
```

Apio Architect invokes this method whenever a `POST` request is received at `http://server_url/api/category/{categoryId}/blog-posting`, filling the `id` parameter with the `categoryId` from the URI. As before, the new `BlogPosting` is parsed from the request body and provided to the method as the `blogPosting` argument. 
