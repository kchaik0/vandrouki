package kchaiko.vandrouki.di.modules

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.parsers.HtmlParser

@Module
class HtmlParserModule {

    @Provides
    fun provideHtmlParser() = HtmlParser()

}