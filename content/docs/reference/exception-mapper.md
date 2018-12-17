---
title: "Exception mappers"
weight: 3
---

Together with [providers](/docs/reference/providers.html), **exception mappers** are a way to override the APIO Architect runtime.

Exception mappers allow you to automatically convert an exception of your domain like `NoSuchUserException` or `NotAuthenticatedException` into HTTP domain exceptions, with its related status code.

Creating an exception mapper requires implementing the `ExceptionMapper` interface, that has a generic parameter to indicate the exception type and exposing it as an OSGi `Component`. This interface has a sole method `map` which receives the exception and returns an `APIError`. APIO Architect will use the `APIError` instance to serialize the response in the correspondent format.

A simple example would be:

```java
import com.liferay.apio.architect.error.APIError;
import com.liferay.apio.architect.exception.mapper.ExceptionMapper;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.NotFoundException;

@Component
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

  @Override
  public APIError map(NotFoundException exception) {
    return new APIError(exception, "Not Found", "not-found", 404);
  }

}
```

Now, everytime one of your [actions](/docs/reference/actions.html) throw a `NotFoundException` APIO Architect will convert your exception using the previously built mapper and will return a nicely formatted response:

```json json
{
  "detail": "Unable to get blog posting comment 1111",
  "status": 404,
  "title": "Not Found",
  "type": "not-found"
}
```

```json hal
{
  "description": "Unable to get blog posting comment 1111",
  "statusCode": 404,
  "title": "Not Found"
}
```

```json json-ld
{
  "description": "Unable to get blog posting comment 1111",
  "statusCode": 404,
  "title": "Not Found",
  "@type": "not-found"
}
```