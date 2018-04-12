package kchaiko.vandrouki.di.component

import dagger.Component
import kchaiko.vandrouki.viewmodel.MainViewModel
import kchaiko.vandrouki.viewmodel.SplashViewModel
import javax.inject.Singleton

@Component
@Singleton
interface RepositoryComponent {

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: SplashViewModel)

}