package kchaiko.vandrouki.viewmodel.provide

import kotlinx.coroutines.experimental.Job

interface ProvideDataViewModel {

    fun provideData(): Job

}