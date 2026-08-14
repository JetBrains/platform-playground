package org.intellij.sdk.passwordSafeApiKeys

import com.intellij.credentialStore.Credentials
import kotlin.collections.fill

class ApiKey(private val value: CharArray) : AutoCloseable {
    fun toCredentials() = Credentials(user = null, value)

    override fun close() {
        value.fill('\u0000')
    }

    fun isEmpty() = value.isEmpty()

    companion object {
        fun of(credentials: Credentials): ApiKey? = credentials.password?.toCharArray()?.let {
            ApiKey(it)
        }
    }
}
