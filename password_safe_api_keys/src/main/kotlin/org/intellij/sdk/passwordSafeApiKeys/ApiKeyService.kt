package org.intellij.sdk.passwordSafeApiKeys

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.credentialStore.generateServiceName
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.components.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val SERVICE_NAME = "Credentials Showcase"

@Service
class ApiKeyService {
    private val serviceName = generateServiceName(SERVICE_NAME, "API Key")

    private val credentialAttributes = CredentialAttributes(serviceName)

    suspend fun save(apiKey: ApiKey) = withContext(Dispatchers.IO) {
        PasswordSafe.instance[credentialAttributes] = apiKey.toCredentials()
    }

    suspend fun find(): ApiKey? = withContext(Dispatchers.IO) {
        PasswordSafe.instance[credentialAttributes]?.toApiKey()
    }

    private fun Credentials.toApiKey() = ApiKey.of(this)
}
