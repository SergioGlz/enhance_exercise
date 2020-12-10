package dev.enhance.exercise.core.configuration.di.modules

import dagger.Module
import dagger.Provides
import dev.enhance.exercise.core.repository.Api
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun api(): Api = Api.create()

}