---
title: "Exception Mappers"
weight: 3
---

Together with [providers](/docs/reference/providers.html), exception mappers are a way to override the Apio Architect runtime. Exception mappers let you automatically convert an exception of your domain (e.g., `NoSuchUserException` or `NotAuthenticatedException`) into an HTTP-domain exception with the related status code. 

To create an exception mapper, implement the `ExceptionMapper` interface and expose the class as an OSGi component. This interface has a generic parameter for the exception type, and a single method (`map`) that receives the exception and returns an `APIError`. Apio Architect uses the `APIError` to serialize the response in the corresponding format.

Here's an example exception mapper for `NotFoundException`: 

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

With this exception mapper, each time an [action](/docs/reference/actions.html) throws a `NotFoundException`, Apio Architect converts it and returns a nicely formatted response:

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
