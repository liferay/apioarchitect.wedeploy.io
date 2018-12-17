---
title: "What is APIO"
weight: 7
---

Apio Architect is a server-side library that facilitates the creation of Apio REST APIs. It's also opinionated to reduce the amount of code API developers have to write. This is also achieved by out-of-the-box implementations of well known patterns in REST APIs, such as the Collection Pattern.

Two key techniques make this possible:

- **Hypermedia**: The same links and forms that we all use every day in a browser can also be applied to APIs to get the same great decoupling and flexibility.

- **Shared Vocabularies**: Instead of returning JSON/XML with attributes tied to the names of the internal models, use standard vocabularies that are well thought out by standardization bodies (such as schema.org or IANA). Even if you have to create your own type because a standard doesn't exist, define it explicitly to be decoupled from any changes that you can make to the internal model.