---
title: "How do I declare actions?"
order: 2
---

Declaring a set of actions on a resource with Apio Architect is easy as pie. We will simply have to create a class that will act as a router for our actions. This class will have to implement the `ActionRouter` interface, which will have a generic type, corresponding to the type for which we are going to declare the actions. Actions will just be annotated methods inside this class.

In addition, for Apio Architect's machinery to be able to find our class, we must convert it to an OSGi component, by annotating it with [`@Component`](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#org.osgi.service.component.annotations.Component).

> The generic type of `ActionRouter` must be a valid Apio Architect's type (see [the documentation on types](/docs/reference/types.html) for more information).

For example, if we want to create actions for a [`BlogPosting`](/docs/reference/types.html#blog-posting) type we should create the next class:

```java
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {
    
}
```

Now we are ready to create our resource's actions.

# What kind of actions can I create with Apio Architect?

Apio Architect includes annotations for the most typical CRUD operations, which can be declared for both collections and individual elements.

- [`@Retrieve`](/docs/reference/actions/which-actions/retrieve.html): for obtaining elements
- [`@Create`](/docs/reference/actions/which-actions/create.html): for creating elements
- [`@Remove`](/docs/reference/actions/which-actions/remove.html): for removing elements
- [`@Replace`](/docs/reference/actions/which-actions/replace.html): for replacing elements

## What about non-CRUD actions?

If you want to declare an action that isn't covered by the previous ones, you can use the meta-annotation `@Action`.

Check [this reference](/docs/reference/actions/which-actions/non-crud.html) for more information about it.