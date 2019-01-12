---
title: "Declaring Endpoints"
weight: 7
---

REST endpoints provide access to resources. Endpoints have a URL and an HTTP method (`POST`, `PUT`, etc...) that binds them to a specific place with a specific contract (the method). Endpoint information usually specifies the endpoint's URI, method, and output media type.

Apio Architect aims to help API developers focus on resources instead of URIs. Instead of directly declaring endpoints, Apio Architect API developers must declare which resource actions are available, and group them by using an `ActionRouter<T>`. Developers can therefore abstract away the logic for handling URLs, verbs, and media-types. This lets them focus on the semantics of their actions. 

## Declaring an Action

Declaring an action is straightforward. For example, to create an endpoint to retrieve (`GET`) a `BlogPosting` resource, annotate the method with `@Retrieve`: 

```java
@Retrieve
BlogPosting retrieve(@Id long id) {
    //Call internal logic to retrieve BlogPosting here
}
```

With the `@Retrieve` annotation and the information provided by the `BlogPosting` [type definition](../types.html), Apio Architect maps the endpoint to a `GET` request to the `BlogPosting` URI. Apio Architect also takes into account the information provided in parameters (e.g., the `@Id` annotated parameter) to build the URI mapping. You'll see this in depth later. 

## Grouping Actions with Routers

For Apio Architect to group actions related to the same resource, you must implement the `ActionRouter<T>` interface, implementing your endpoints via actions. The type argument (`T`) you use with `ActionRouter` must be a valid [Apio Architect type](/docs/reference/types.html). For Apio Architect to detect and register your Router, you must declare your `ActionRouter` implementation as an OSGi [`@Component`](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#org.osgi.service.component.annotations.Component). 

For example, the following Router contains the endpoint actions for a [`BlogPosting`](/docs/reference/types.html#blog-posting):

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

## What Kind of Actions Can I Declare with Apio Architect?

Apio Architect includes annotations for the most common CRUD operations, which you can declare for collections and individual elements:

-   [`@Retrieve`](/docs/reference/actions/which-actions/retrieve.html): Get elements
-   [`@Create`](/docs/reference/actions/which-actions/create.html): Create elements
-   [`@Remove`](/docs/reference/actions/which-actions/remove.html): Remove elements
-   [`@Replace`](/docs/reference/actions/which-actions/replace.html): Replace elements

## What About non-CRUD Actions?

To declare an action that isn't covered by the above annotations, use the meta-annotation `@Action`. For more information, see the reference documentation on [non-CRUD actions](/docs/reference/actions/which-actions/non-crud.html). 
