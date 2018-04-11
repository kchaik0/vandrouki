package kchaiko.vandrouki.di.modules

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PicassoModule(private val appContext: Application) {

    @Provides
    @Singleton
    fun providePicasso(): Picasso = Picasso.with(appContext)
}