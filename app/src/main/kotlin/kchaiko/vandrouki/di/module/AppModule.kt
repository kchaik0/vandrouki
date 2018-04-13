package kchaiko.vandrouki.di.module

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.network.service.LoadUrlService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(): LoadUrlService = Retrofit.Builder()
            .baseUrl("https://vandrouki.by")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoadUrlService::class.java)

}