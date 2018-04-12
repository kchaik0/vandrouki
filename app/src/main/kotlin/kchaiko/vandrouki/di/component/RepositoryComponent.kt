package kchaiko.vandrouki.di.component

import dagger.Component
import kchaiko.vandrouki.di.modules.HtmlParserModule
import kchaiko.vandrouki.di.modules.RepositoryModule
import kchaiko.vandrouki.viewmodel.MainViewModel
import kchaiko.vandrouki.viewmodel.SplashViewModel
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class, HtmlParserModule::class])
@Singleton
interface RepositoryComponent {

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: SplashViewModel)

}