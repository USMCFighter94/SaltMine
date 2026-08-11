package dev.kolin.saltmine.core.network

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.ktor.client.HttpClient

@ContributesTo(AppScope::class)
@BindingContainer
public object NetworkBindings {
    @Provides
    public fun provideHttpClient(): HttpClient = createHttpClient()
}
