---
title: "Remove"
order: 3
---

Performed by executing a `DELETE` request on the resource's URI. 

When a method is annotated with `@Remove` the library is told that that method is in charge of "removing" data. This annotation can only be used to declare actions that remove individual elements.

### Using `@Remove` to delete a single element

To tell Apio Architect that this action removes a single `BlogPosting` we just need to add a parameter of the same type as our `BlogPosting`'s `ID` and annotate it with `@Id`.

> Remove actions are [idempotent](https://developer.mozilla.org/en-US/docs/Glossary/Idempotent), so it must always return `void`. Apio Architect will return a [`204`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/204) as the API response.

```java
import com.liferay.apio.architect.annotation.Actions.Remove;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Remove
    void removeBlogPostingWithId(@Id long id);
    
}
```

Now performing a `DELETE` request to `http://server_url/api/blog-posting/{id}` will remove the blog posting with the provided `id`. 