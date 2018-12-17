---
title: "What is APIO"
weight: 7
---

Apio Architect is a server-side library that facilitates the creation of REST APIs that are easily consumed by a wide range of consumer types while fostering practices that improve the evolvability minimizing the need to change the code of any API consumers.

The goal of Apio is to reduce the amount of code that the API developers need to write by letting them focus on the model exposed and on the  implementation of the API services. 

Apio will enrich the endpoints by providing support for well known features and usage patterns in REST APIs, such us navigability, filtering, embedding, content negotiation of different Hypermedia types...

Resulting APIs will leverage two key techniques to achieve the aforementioned goals:

- **Hypermedia**: The same links and forms that we all use every day in a browser can also be applied to APIs to get the same great decoupling and flexibility.

- **Shared Vocabularies**: Instead of returning JSON/XML with attributes tied to the names of the internal models, use standard vocabularies that are well thought out by standardization bodies (such as schema.org or IANA). Even if you have to create your own type because a standard doesn't exist, define it explicitly to be decoupled from any changes that you can make to the internal model.