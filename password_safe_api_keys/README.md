# Persisting API Keys Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Persisting Sensitive Data in IntelliJ SDK Docs][docs:sensitive-data], [Settings Tutorial in IntelliJ SDK Docs][docs:settings]*

## Quickstart

Persisting API Keys Sample demonstrates how to collect an API key in a settings page and store it in the secure [`PasswordSafe`][api:password-safe] instead of regular settings.

Open **Settings | Tools | Credentials**, enter an API key, and click **Apply**. The value is stored securely and the password field is redacted. When the settings page is opened again, the field indicates that a value is stored without revealing the API key.

## How It Works

1. The settings page loads the existing credential to determine whether an API key is stored.
2. The password field remains empty; the stored API key is never displayed.
3. Editing the field marks the settings as modified and removes the stored-value indicator.
4. Applying the settings copies the value into an [`ApiKey`][file:ApiKey], stores it through `PasswordSafe`, and clears the UI field.

## Credential Storage

[`ApiKeyService`][file:ApiKeyService] provides access to `PasswordSafe` with appropriate thread handling.

It uses stable [`CredentialAttributes`][api:credential-attributes] generated from the plugin name and credential type for both reads and writes.

- There is no notion of an username, as the API key is stored as a single credential.
The underlying [`Credentials`][api:credentials] have the `user` property set to `null`.
- Password-safe access runs on `Dispatchers.IO` as it is a potentially long-running operation that is prohibited on the EDT thread.
- The API key is represented as a `CharArray` rather than an immutable `String`.
- [`ApiKey.close()`][file:ApiKey] clears the character array after use to reduce the risk of accidental exposure.
- An empty API key is rejected by the settings validation and is not saved.
- The stored value is not copied into the UI element to prevent unnecessary and insecure in-memory storage.


## Extension Points

| Name | Implementation | Extension Point Class |
|------|----------------|-----------------------|
| `com.intellij.applicationConfigurable` | [`CredentialsConfigurable`][file:CredentialsConfigurable] | `Configurable` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html
[docs:settings]: https://plugins.jetbrains.com/docs/intellij/settings-tutorial.html
[docs:sensitive-data]: https://plugins.jetbrains.com/docs/intellij/persisting-sensitive-data.html

[api:credential-attributes]: https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/credentialStore/CredentialAttributes.kt
[api:password-safe]: https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/ide/passwordSafe/PasswordSafe.kt
[api:credentials]: https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/credentialStore/CredentialAttributes.kt#L63

[file:ApiKey]: ./src/main/kotlin/org/intellij/sdk/passwordSafeApiKeys/ApiKey.kt
[file:ApiKeyService]: ./src/main/kotlin/org/intellij/sdk/passwordSafeApiKeys/ApiKeyService.kt
[file:CredentialsConfigurable]: ./src/main/kotlin/org/intellij/sdk/passwordSafeApiKeys/CredentialsConfigurable.kt
