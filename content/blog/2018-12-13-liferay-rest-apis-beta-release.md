---
author: Pablo Agulla
banner: /images/blogs/banner.jpg
date: 2018-12-13
title: Liferay REST APIs Beta release
description: I’m happy to announce the release of the first beta for the new set of Liferay REST APIs.
needsAuth: false
---

I’m happy to announce the release of the first beta for the new set of Liferay REST APIs. But before going into details, for those of you who might not be aware of this project, I’d like to give you some context.

## Our vision

Over the last year and a half, we have been working on a new set of REST APIs for Liferay following industries best practices. The goal of these APIs is to allow developers to access and manage all the content stored in Liferay in an easy way, so you can focus on building great experiences and not having to struggle with how to get that content.

Our long-term goal is to satisfy the needs of the different Liferay users, covering all the important use cases by offering the needed features by each one of them. Before fulfilling the whole vision and with the feedback received from several customers that we interviewed, we decided to focus first on helping you deliver content to your end users.

![Liferay Users](/images/blogs/liferay_users.png)

## What will you find on this beta

Now that we are all on the same page, let’s talk about what will you find in this first version of the APIs. The scenario that we had in mind while building them was to allow developers to build their own frontend to expose the content created and stored in Liferay. With that objective we prioritized the features to implement in order to support, among others, the following use cases:

- Access published structured content. By doing so, you’ll be able to create for example an SPA to show the latest news from your company.
    - To help you find the right content, we’ve implemented several filtering and sorting options, such as: filter and sort by title, publishing date, tags, etc.
- Access the documents and media repositories. Enrich your experiences by having at your disposal the whole media library that you are already benefiting from.
- Access the blog entries that are already published. Access the content and expose it with the best appearance your developers can think of.

Apart from these three main use cases, we are making possible to access and perform actions for other types of information. To check the whole set of resources and actions available in this beta, see the first draft of the documentation and the Open API profile [here](https://headlessapis.wedeploy.io).

## How can I get access to the beta

We hope that by this time you are eager to give it a try to these APIs, test them, check if they could fit in your projects and give us feedback to make them greater. To make it more convenient for you, we have prepared a docker image containing the latest Liferay DXP 7.1 version with the first beta installed. In doing so, we provide you with a full sandbox to play with, not affecting your current environments.

In order to get the docker image and run the beta, follow the installation instructions:

- Pre requirements: install Docker to run the beta image
- Create the config file `com.liferay.apio.architect.internal.application.ApioApplication-default.config`. 
- Add the following properties to the file:

```properties
auth2.scopechecker.type="none" auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes="*"
auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes="*"
auth.verifier.guest.allowed="true"
```

Note that the last property (`auth.verifier.guest.allowed`) lets guests access public content via the APIs. To turn this off, set the property to false.

Store this file under _"/Users/liferay/Downloads/xyz123/files/osgi/configs"_ being _"/Users/liferay/Downloads/"_ the folder where the docker image will be downloaded.

- Execute the following command: 
    ```
    docker run -it -p 8080:8080 \
        -v /Users/liferay/Downloads/xyz123:/etc/liferay/mount \
        liferay/portal-snapshot:7.1.x-201812071242-af6321a
    ```
    - Substitute _"/Users/liferay/Downloads/"_ with the folder where the docker image will be downloaded.
    - **Note:** this command will download the docker image for you if it does not find it in your computer.

We know that some of you would like to test the APIs in your own environments so additionally, we will release the APIs as a marketplace app during January. We’ll make a separate announcement once the app is available.

Be aware that **this is a beta product** so we strongly recommend to not use them for production projects and we expect several breaking changes before the final version is released in order to take into account your feedback.

## How to give feedback

Once you had given them a chance (or while testing them) we would like you to tell us about the experience using them: have you found any error?, do you miss a feature that you think is crucial? Just access the Liferay portal Jira project [here](https://issues.liferay.com/projects/LPS/summary) and create an issue setting the component to _“HeadlessCMS”_ (the most common issue types will be bug or feature request). We’ll give you an answer as soon as possible.

> This article was originally posted on the [Liferay Community Site](https://community.liferay.com). See the original post [here](https://community.liferay.com/blogs/-/blogs/liferay-rest-apis-beta-release).