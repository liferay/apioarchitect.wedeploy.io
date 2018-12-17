---
title: "Why should I use APIO"
weight: 7
---

As an API developer, you will have to provide the code that carries out the core of your API goal: maybe executing some internal services, accessing the DB or invoking some other external systems. 

Once you have the data you will need to convert it to the model that you specified in your API contract and send it back to the client as a properly formatted response.

But, beyond these two basic tasks -getting the data and sending back the response- there are a bunch of hidden tasks regarding the parse of the request and formating the response in the way that clients of the API are expecting it.

<!-- TODO add links to the different pages explaining each feature -->
Features as filtering or ordering collections, pagination, sparse fieldsets, embedding nested or related resources, support for  one or more different Hypermedia formats (such HAL, JSON-LD, Siren, ...) are usually common tasks that populate the API developer backlog.

**APIO helps you to get rid of those extra tasks** by adding automatically support for the handling of the request and formating the response, **allowing you to focus just on the endpoint implementation**.
