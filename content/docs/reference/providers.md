---
title: "Providers"
weight: 4
---

A Provider is a extension point in Apio that let you extract values from the request, and use this values in several places without having to repeat the same code one time and another.

If you are familiar with the JAX-RS Context providers, Apio Providers follow the same approach.

Creating one Provider is very easy, the only thing you have to do is implement the `Provider` interface, which consist only in one method `public T createContext(HttpServletRequest httpServletRequest);`

A simple example:

```java
@Component(service = Provider.class)
public class ApplicationURLProvider implements Provider<ApplicationURL> {

	@Override
	public ApplicationURL createContext(HttpServletRequest httpServletRequest) {
		return getServerURL(httpServletRequest) + httpServletRequest.getContextPath();
	}

}
```

As you can see in the previous snippet we implement a method and extract the needed value out of the http request.

Afterdeploying the component, Apio will be able to inject the ApplicationURL in all the [actions](/docs/reference/actions.html) that need it.
