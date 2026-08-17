# IntelliJ Platform SDK Playground

[![official JetBrains project](https://jb.gg/badges/official-flat-square.svg)][jb:github]
[![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg?style=flat-square)][jb:docs]
[![Bluesky](https://img.shields.io/badge/%40platform.jetbrains.com-0285FF?logo=bluesky&logoColor=fff)][jb:bsky]
[![X](https://img.shields.io/badge/%40JBPlatform-1DA1F2?logo=x)][jb:x]
[![Build](https://img.shields.io/github/actions/workflow/status/JetBrains/intellij-sdk-docs/code-samples.yml?branch=main&style=flat-square)][gh:workflow-code-samples]
[![JetBrains Platform Forum Status](https://img.shields.io/discourse/status?server=https%3A%2F%2Fplatform.jetbrains.com)][jb:forum]

A collection of IntelliJ Platform plugins that demonstrate various features for [JetBrains products][jb:products].
This is an auxiliary repository for the [IntelliJ SDK Code Samples][repo:code-samples].
It contains code samples that accompany various blogs, [JetBrains Platform][jb:forum] Forum posts, and videos.

> [!TIP]
> Official IntelliJ Platform SDK code samples can be found in the [IntelliJ SDK Code Samples](https://github.com/JetBrains/intellij-sdk-code-samples) mirror repository.

## Target Platform

Each code sample declares a specific compatibility range.
Generally, code samples target the _Latest GA_ release and the previous two major releases.

## Structure

Code samples use the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as their build system.

## Code Samples

Please see [Code Samples][docs:code-samples] topic on how to import and run code samples.

In the following table, you may find all available samples provided in the separated directories as stand-alone projects available for running with the Gradle `runIde` task.

| Code Sample                                     | Description                                                                                                                                                       |
|-------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Persisting API Keys](./password_safe_api_keys) | Persisting API key in the `PasswordSafe` along with a Settings pane UI.

[gh:workflow-code-samples]: https://github.com/JetBrains/intellij-sdk-docs/actions/workflows/code-samples.yml
[gh:template]: https://github.com/JetBrains/intellij-platform-plugin-template

[jb:github]: https://github.com/JetBrains/.github/blob/main/profile/README.md
[jb:docs]: https://plugins.jetbrains.com/docs/intellij/
[jb:products]: https://www.jetbrains.com/products.html
[jb:forum]: https://platform.jetbrains.com
[jb:bsky]: https://bsky.app/profile/platform.jetbrains.com
[jb:x]: https://x.com/JBPlatform

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:code-samples]: https://plugins.jetbrains.com/docs/intellij/code-samples.html
[docs:eps]: https://plugins.jetbrains.com/docs/intellij/extension-point-list.html
[docs:gradle]: https://plugins.jetbrains.com/docs/intellij/developing-plugins.html
[docs:plugin.xml]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html
[docs:explore-api]: https://plugins.jetbrains.com/docs/intellij/explore-api.html

[repo:code-samples]: https://github.com/JetBrains/intellij-sdk-code-samples
