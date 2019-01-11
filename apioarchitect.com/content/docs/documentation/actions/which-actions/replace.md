---
title: "Replace"
order: 4
---

Represents a `PUT` request on the resource's URI to replace an element. 

A method annotated with `@Replace` will instruct Apio Architect to map `PUT` requests to the mehod. This annotation can only be used to declare actions that replace individual elements.

### Using `@Replace` to replace a single element

The `@Replace` should declare, as method parameters, the `@Id` of the resource that will be parsed from the URI, and the resource itself, retrieved from the request's `@Body`.

> To create the parameter annotated with `@Body` Apio Architect will use the information obtained from the `BlogPosting`'s annotations. See [`@Field#mode`](/docs/reference/types.html#mode) for more information.

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

Apio Architect will map to the `replaceBlogPostingWithId` mehtod to every `PUT` request sent to `http://server_url/api/blog-posting/{id}`.`id`. 