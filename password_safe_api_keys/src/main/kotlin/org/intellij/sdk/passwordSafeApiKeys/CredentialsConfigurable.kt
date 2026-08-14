package org.intellij.sdk.passwordSafeApiKeys

import com.intellij.openapi.components.service
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.platform.ide.progress.ModalTaskOwner
import com.intellij.platform.ide.progress.runWithModalProgressBlocking
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class CredentialsConfigurable : BoundConfigurable("Credentials") {

    private lateinit var apiKeyField: JBPasswordField

    private var modified: Boolean = false

    /**
     * Suppress [DocumentListener]s when the API Key field is updated programmatically.
     */
    private var suppressApiKeyListener = false

    override fun createPanel() = panel {
        row("API Key:") {
            apiKeyField = passwordField()
                .align(AlignX.FILL)
                .applyToComponent {
                    document.addDocumentListener(apiKeyFieldListener)
                }
                .validationOnInput { comp ->
                    if (!modified) return@validationOnInput null
                    comp.useApiKey { apiKey ->
                        if (apiKey.isEmpty()) this.error("Cannot be empty") else null
                    }
                }
                .component
        }
    }

    override fun apply() {
        super.apply()
        if (!modified) return
        apiKeyField.useApiKey { apiKey ->
            if (apiKey.isEmpty()) return@useApiKey
            invoke("Securely storing API key") {
                service<ApiKeyService>().save(apiKey)
            }
            redact(true)
            modified = false
        }
    }

    override fun reset() {
        val apiKey = invoke("Retrieving API key") {
            service<ApiKeyService>().find()
        }
        apiKey.use {
            redact(it != null)
        }
        super.reset()
    }

    override fun disposeUIResources() {
        if (::apiKeyField.isInitialized) {
            suppressApiKeyListener = true
            apiKeyField.text = ""
            suppressApiKeyListener = false
        }
        super.disposeUIResources()
    }

    private fun redact(isStored: Boolean) {
        apiKeyField.setPasswordIsStored(isStored)
        modified = false
        suppressApiKeyListener = true
        apiKeyField.text = ""
        suppressApiKeyListener = false
        apiKeyField.transferFocusUpCycle()
    }

    override fun isModified() = modified

    private val apiKeyFieldListener = object : DocumentListener {
        private fun updateModified() {
            if (suppressApiKeyListener) return
            modified = true
            apiKeyField.setPasswordIsStored(false)
        }

        override fun insertUpdate(e: DocumentEvent?) = updateModified()
        override fun removeUpdate(e: DocumentEvent?) = updateModified()
        override fun changedUpdate(e: DocumentEvent?) = updateModified()
    }

    private fun <T> invoke(title: String, action: suspend () -> T): T {
        return runWithModalProgressBlocking(ModalTaskOwner.guess(), title) {
            action()
        }
    }

    private fun <T> JBPasswordField.useApiKey(action: (ApiKey) -> T): T {
        return ApiKey(getPasswordOrEmpty()).use {
            action(it)
        }
    }

    private fun JBPasswordField.getPasswordOrEmpty(): CharArray {
        return try {
            password ?: charArrayOf()
        } catch (_: NullPointerException) {
            // JPasswordField.getPassword() may throw if the underlying document is null.
            charArrayOf()
        }
    }
}
