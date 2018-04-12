package kchaiko.vandrouki.di.modules

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.parsers.HtmlParser
import kchaiko.vandrouki.repository.DiscountRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDiscountRepository(htmlParser: HtmlParser) = DiscountRepository(htmlParser)

}