---
title: "Remove"
order: 3
---

Represents a `DELETE` request on the resource's URI. 

A method annotated with `@Remove` will the library will instruct Apio Architect to map a `DELETE` method to the URI representing the Resource's type of the `ActionRouter`. This annotation can only be used to declare actions that remove individual elements.

### Using `@Remove` to delete a single element

The `@Remove` action's method should include an argument annotated with `@Id` which Apio Architect will use to parse and provide the resource's ide from the URI.

> Remove actions are [idempotent](https://developer.mozilla.org/en-US/docs/Glossary/Idempotent), so it must always return `void`. Apio Architect will return a [`204`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/204) as the API response.

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

Apio Architect will invoke the `removeBlogPostingWithId`method on any `DELETE` request received on `http://server_url/api/blog-posting/{id}`, providing the `{id}` from the URI as parameter.