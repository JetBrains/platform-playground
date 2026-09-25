package org.intellij.sdk.persistence

import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

private const val COFFEE_COUNT_KEY = "org.intellij.sdk.persistence.coffeeCount"

class EnjoyCoffeeAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Notification("Coffee", "Coffee was enjoyed", getCounterMessage(), NotificationType.INFORMATION)
            .addAction(NotificationAction.createSimpleExpiring("Reset counter") {
                resetCounter()
            })
            .notify(e.project)
    }

    private fun getCounterMessage() = when (val count = incrementCounter()) {
        1 -> "One coffee has been enjoyed so far"
        else -> "$count coffees have been enjoyed so far"
    }

    private fun incrementCounter(): Int {
        val properties = PropertiesComponent.getInstance()
        val newCount = properties.getInt(COFFEE_COUNT_KEY, 0) + 1
        properties.setValue(COFFEE_COUNT_KEY, newCount, 0)
        return newCount
    }

    private fun resetCounter() {
        PropertiesComponent.getInstance().setValue(COFFEE_COUNT_KEY, 0, 0)
    }
}