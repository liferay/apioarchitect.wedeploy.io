---
title: "Remove"
order: 3
---

The `@Remove` annotation represents a `DELETE` request to the resource's URI. Annotating a method with `@Remove` instructs Apio Architect to map a `DELETE` method to the URI for the resource's type. This annotation can only be used to declare actions that remove individual elements.

## Using @Remove to Delete a Single Element

Methods annotated with `@Remove` must have an argument annotated with `@Id`. Apio Architect uses this to parse and provide the resource's ID from the URI. 

> Note that remove actions are [idempotent](https://developer.mozilla.org/en-US/docs/Glossary/Idempotent), and therefore must always return `void`. Apio Architect returns a [`204`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/204) as the API response. 

For example, here's a Router for the `BlogPosting` type that contains a `removeBlogPostingWithId` method annotated with `@Remove`. The `id` parameter is annotated with `@Id`:

```java
import com.liferay.apio.architect.annotation.Actions.Remove;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
    @Remove
    void removeBlogPostingWithId(@Id long id) {
        //remove BlogPosting with id from persistent storage
    }
    
}
```

Apio Architect invokes the `removeBlogPostingWithId` method on any `DELETE` request to `http://server_url/api/blog-posting/{id}`, providing the `{id}` from the URI as the method's parameter. 
