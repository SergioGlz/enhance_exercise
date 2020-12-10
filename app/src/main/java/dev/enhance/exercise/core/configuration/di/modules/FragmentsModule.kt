package dev.enhance.exercise.core.configuration.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.enhance.exercise.spacex.SpaceXFragment

@Module
interface FragmentsModule {

    @ContributesAndroidInjector
    fun contributeSpaceXFragment(): SpaceXFragment

}