---
title: "What is APIO"
weight: 7
---

APIO Architect is a server-side library that facilitates the creation of evolvable REST APIs that many different consumer types can use. Evolvable APIs minimize the need for consumers to change in response to API changes. By letting developers focus on the model exposed and the API service implementation, APIO aims to reduce the amount of code that developers must write. APIO also supports well known features and usage patterns in REST APIs (e.g., navigability, filtering, embedding, content negotiation of different hypermedia types, etc.). 

To make all this happen, APIs developed with APIO leverage two key techniques: 

- **Hypermedia**: When used in APIs, hyperlinks bring flexibility and decoupling between APIs and their consumers. This isn't a new concept. [Hypermedia as the Engine of Application State (HATEOAS)](https://en.wikipedia.org/wiki/HATEOAS) specifies that an API uses hypermedia to provide the information that clients need to use the API. 

- **Shared Vocabularies**: Instead of returning JSON/XML with attributes tied to the names of the internal models, APIs developed with APIO use standard vocabularies that are well thought out by standardization bodies (such as schema.org or IANA). Even if such a standard doesn't exist for your type, you can define it explicitly to be decoupled from any changes that you make to the internal model. 
