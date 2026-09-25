# Persisting State with PropertiesComponent Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Persisting State of Components in IntelliJ SDK Docs][docs:persisting-state], [Action System in IntelliJ SDK Docs][docs:actions], [Notifications in IntelliJ SDK Docs][docs:notifications]*

## Quickstart

Persisting State with `PropertiesComponent` Sample demonstrates how to persist simple primitive values across IDE restarts.

To try out the sample:
1. Run the plugin using the `runIde` Gradle task.
2. Select **Tools | Enjoy Coffee** from the main menu.
3. A balloon notification appears displaying the total count of coffees enjoyed.
4. Click **Reset counter** inside the notification balloon to reset the counter back to zero.
5. Restart the IDE; the counter value persists across restarts.

## Extension Points

| Name | Implementation | Extension Point Class |
|---|---|---|
| `com.intellij.notificationGroup` | Group ID: `Coffee` | `NotificationGroupEP` |

*Reference: [Notifications in IntelliJ SDK Docs][docs:notifications], [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/action-system.html
[docs:notifications]: https://plugins.jetbrains.com/docs/intellij/notifications.html
[docs:persisting-state]: https://plugins.jetbrains.com/docs/intellij/persisting-state-of-components.html

[api:properties-component]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/ide/util/PropertiesComponent.java
[api:notification-action]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/notification/NotificationAction.java

[file:EnjoyCoffeeAction]: ./src/main/kotlin/org/intellij/sdk/persistence/EnjoyCoffeeAction.kt
