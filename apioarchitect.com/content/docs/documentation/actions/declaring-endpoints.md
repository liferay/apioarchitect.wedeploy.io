---
title: "Declaring Endpoints"
weight: 7
---

In a typical REST scenario, we have endpoints, which provide the access a resource. Endpoints have an URL and an HTTP method (`POST`, `PUT`...) that binds them to a specific place with a specific contract (the method).

The endpoint information usually specifies the URI where the endpoints is mapped, along with the method and output media type information.

Apio Architect aims to make the API developer focus on the Resources rather than to focus on thinking about URIs. Instead of directly declaring endpoints, the API developer will need to declare which `@Action`s are available on the resources, and group them using an `ActionRouter<T>`.

 That way, the developer can abstract away from all the logic of handling URLs, verbs, and media-types, allowing them to focus only on the important things: **the semantics of the action**.

## Declaring an action

Apio Architect aims to make the API developer focus on the Resources rather than to focus on thinking about URIs. Instead of directly declaring endpoints, the API developer will need define which **`@Action`s are available to be invoked on the resources**. That way, the developer can abstract away from all the logic of handling URLs, verbs, and media-types, and can focus only on the important things: **the semantics of the action**.

Thus, an endpoint to `GET` (or retrieve) a `BlogPosting` resource will be written with Apio Architect as simply as:

```java
@Retrieve
BlogPosting retrieve(@Id long id) {
    //Call internal logic to retrieve BlogPosting here
}
```

With just that annotation, and with all the information provided by the `BlogPosting` [Type definition](../types.html), Apio Architect will be able to map the endpoint to a `GET` request to the URI associated with the BlogPosting URI.

Also, Apio Architect will take into account the information provided in parameters (such as the `@Id` annotated parameter) to build the URI mapping. But we will see this in depth later. 

## Grouping actions with routers.

To group all the actions related to the same resource, Apio Architect needs that the API developer to implement the `ActionRouter<T>` interface, which will contain the different endpoints defined through actions.

> The generic type of `ActionRouter` must be a valid Apio Architect's type (see [the documentation on types](/docs/reference/types.html) for more information).

The `ActionRouter<T>` implementation must be declared as an OSGi  [`@Component`](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#org.osgi.service.component.annotations.Component), in order to allow the router to be detected and registered.

For example, the following code shows a router containing the endpoint actions for a [`BlogPosting`](/docs/reference/types.html#blog-posting) type:

```java
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

@Component
public class BlogPostingActionRouter implements ActionRouter<BlogPosting> {

    @Retrieve
    BlogPosting retrieve(@Id long id) {
        //Call internal logic to retrieve BlogPosting here
    }

}
```

# What kind of actions can I declare with Apio Architect?

Apio Architect includes annotations for the most typical CRUD operations, which can be declared for both collections and individual elements.

- [`@Retrieve`](/docs/reference/actions/which-actions/retrieve.html): for obtaining elements
- [`@Create`](/docs/reference/actions/which-actions/create.html): for creating elements
- [`@Remove`](/docs/reference/actions/which-actions/remove.html): for removing elements
- [`@Replace`](/docs/reference/actions/which-actions/replace.html): for replacing elements

## What about non-CRUD actions?

If you want to declare an action that isn't covered by the previous ones, you can use the meta-annotation `@Action`.

Check [this reference](/docs/reference/actions/which-actions/non-crud.html) for more information about it.