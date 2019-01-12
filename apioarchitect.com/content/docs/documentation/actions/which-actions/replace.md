---
title: "Replace"
order: 4
---

The `@Replace` annotation represents a `PUT` request to the resource's URI to replace an element. Annotating a method with `@Replace` instructs Apio Architect to map `PUT` requests to the method. You can only use this annotation to declare actions that replace individual elements. 

## Using @Replace to Replace a Single Element

Methods annotated with `@Replace` must have these parameters: 

-   A parameter for the resource's ID, annotated with `@Id`. Apio Architect parses this from the URI and passes it to the method.
-   A parameter of the same type as the resource, annotated with `@Body`. This instructs Apio Architect to retrieve the parameter value from the request's body, using the `mode` property of the resource type's `@Field` annotation. For more information, see the Field section in the [Types documentation](/docs/documentation/types.html). 

For example, here's a Router for the `BlogPosting` type that contains a `replaceBlogPostingWithId` method annotated with `@Replace`. The `id` parameter is annotated with `@Id` and the `BlogPosting` parameter is annotated with `@Body`:

```java
import com.liferay.apio.architect.annotation.Actions.Replace;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Replace
    BlogPosting replaceBlogPostingWithId(@Id long id, @Body BlogPosting blogPosting) {
        //Code to replace the stored blogPosting with specified id with the new value
    }
    
}
```

Apio Architect maps this method to each `PUT` request sent to `http://server_url/api/blog-posting/{id}`, providing the `{id}` from the URI as the method's `id` parameter. 
