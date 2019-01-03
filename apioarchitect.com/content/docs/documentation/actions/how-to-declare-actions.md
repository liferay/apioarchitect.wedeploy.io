---
title: "How do I declare actions?"
order: 2
---

Declaring a set of actions on a resource with Apio Architect is simple as pie. We will simply have to create a class that will act as a router for our actions. This class will have to implement the `ActionRouter` interface, which will have a generic type, corresponding to the type for which we are going to declare the actions.

> The generic type of `ActionRouter` must be a valid Apio Architect's type (see [the documentation on types](/docs/reference/types.html) for more information).

For example, if we want to create actions for a [`BlogPosting`](/docs/reference/types.html#blog-posting) type we should create the next class:

```java
import com.liferay.apio.architect.router.ActionRouter;

public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
}
```

In addition, for Apio Architect's machinery to be able to find our class, we must convert it to an OSGi component, by annotating it with `@Component`.

```java
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
}
```

Now we are ready to create our resource's actions.