---
title: Running the example
description: Run the OSGi container
stepNumber: 6
---

Now you are ready to run your example. Just execute the following command from your project's root directory:

```bash
./gradlew run
```

Wait a few seconds while the container gets up and try your API by calling your two endpoints.

```bash
curl localhost:8080/api/person -H "Accept: application/json"
```

Which will return the list of persons:

```json
{
  "totalNumberOfItems" : 2,
  "numberOfItems" : 2,
  "self" : "localhost:8080/api/person?page=1&per_page=2",
  "pages" : {
    "first" : "localhost:8080/api/person?page=1&per_page=2",
    "last" : "localhost:8080/api/person?page=1&per_page=2"
  },
  "collection" : "localhost:8080/api/person",
  "elements" : [ 
      {
        "name" : "Alex",
        "jobTitle" : "Developer",
        "self" : "localhost:8080/api/person/1"
      }, {
        "name" : "David",
        "jobTitle" : "Developer",
        "self" : "localhost:8080/api/person/2"
      } 
  ]
}
```

Or:

```bash
curl localhost:8080/api/person/1 -H "Accept: application/json"
```

Which will return a single person:

```json
{
  "name" : "Alex",
  "jobTitle" : "Developer",
  "self" : "localhost:8080/api/person/1"
}
```

Congratulations! You have created your first API with Apio Architect!

# 🎉🎉🎉

If you want to go deeper, feel free to browse the rest of the [documentation](/docs/), where you will find examples by use case and other features of the library.

![end](/images/onboarding/the_end.gif)