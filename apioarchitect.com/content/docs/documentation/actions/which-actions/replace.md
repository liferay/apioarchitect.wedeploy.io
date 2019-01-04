---
title: "Replace"
order: 4
---

Performed by executing a `PUT` request on the resource's URI. 

When a method is annotated with `@Replace` the library is told that that method is in charge of "replacing" data. This annotation can only be used to declare actions that replace individual elements.

### Using `@Replace` to replace a single element

To tell Apio Architect that this action replaces a single `BlogPosting` we just need to add a parameter of the same type as our `BlogPosting`'s `ID` and annotate it with `@Id`. Also, we must add a parameter of the same type as our resource, annotated with `@Body` (this tells the library that this value must be recovered from the request's body).

> To create the parameter annotated with `@Body` Apio Architect will use the information obtained from the `BlogPosting`'s annotations. See [`@Field#mode`](/docs/reference/types.html#mode) for more information.

```java
import com.liferay.apio.architect.annotation.Actions.Replace;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Replace
    BlogPosting replaceBlogPostingWithId(@Id long id, @Body BlogPosting blogPosting);
    
}
```

Now executing a `PUT` request containing a `BlogPosting` as body to `http://server_url/api/blog-posting` will replace the blog posting with the provided `id`. 