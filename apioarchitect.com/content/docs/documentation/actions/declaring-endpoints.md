---
title: "Declaring Endpoints"
weight: 7
---

In a typical REST scenario, we have endpoints, which indicate how to access a resource. Endpoints have an URL and an HTTP method (`POST`, `PUT`...) that binds them to an specific place with an specific contract (the method). For example, this is all you need in typical Java API libraries to create a `GET` endpoint on a `BlogPosting` resource:

```java JAX-RS
@GET
@Path("/blog-posting/{id}")
@Produces(MediaType.APPLICATION_JSON)
public BlogPosting getBlogPosting(@PathParam("id") long id);
```

```java SPRING-BOOT
@RequestMapping(value = "/blog-posting/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public BlogPosting getBlogPosting(@PathVariable("id") long id);
```

As you can see, we need to indicate the URI where that endpoints lives, along with the method and output media type information.

Okay, but how do I write that endpoint with Apio Architect? To begin with, in Apio Architect we don't talk about endpoints, but about actions on resources. Apio Architect allows developers to abstract from all the logic of URLs, verbs and media-types, allowing them to focus only on the important things: the semantics of the action. Thus, the previous example, written with Apio Architect would be:

```java
@Retrieve
BlogPosting retrieve(@Id long id);
```

As you can see, with Apio Architect we don't have to annotate the method with anything that indicates URLs, HTTP verbs, or media-types, just with the type of action that this Java method is going to execute. In addition, we are indicating to the library that this method corresponds to the "retrieve" action of a specific blog-posting, because we have annotated one of its parameters with `@Id` (we will see this in depth later).