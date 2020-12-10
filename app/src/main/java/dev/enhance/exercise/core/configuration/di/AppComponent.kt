package dev.enhance.exercise.core.configuration.di

import androidx.lifecycle.MutableLiveData
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dev.enhance.exercise.core.application.ExampleApplication
import dev.enhance.exercise.core.configuration.di.modules.*
import dev.enhance.exercise.core.repository.Api
import dev.enhance.exercise.spacex.SpaceXRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [FeaturesModule::class, NetworkModule::class, AndroidInjectionModule::class, ActivitiesModule::class, FragmentsModule::class, ViewModelModule::class])
interface AppComponent: AndroidInjector<ExampleApplication> {
    fun api(): Api
    fun loaderRx(): MutableLiveData<Boolean>
    fun errorMessageRx(): MutableLiveData<Int>
    fun githubRepository(): SpaceXRepository
}