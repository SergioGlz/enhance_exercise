package dev.enhance.exercise.core.configuration.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.enhance.exercise.core.configuration.architecture.ViewModelFactory
import dev.enhance.exercise.core.configuration.architecture.ViewModelKey
import dev.enhance.exercise.spacex.SpaceXViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SpaceXViewModel::class)
    internal abstract fun spaceXViewModel(viewModel: SpaceXViewModel): ViewModel

}