package dev.enhance.exercise.core.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dev.enhance.exercise.core.configuration.di.DaggerAppComponent
import javax.inject.Inject

class ExampleApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}