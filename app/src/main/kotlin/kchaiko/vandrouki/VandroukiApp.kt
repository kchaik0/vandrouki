package kchaiko.vandrouki

import android.app.Application
import android.arch.persistence.room.Room
import com.facebook.stetho.Stetho
import kchaiko.vandrouki.db.VandDatabase
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.repository.FavouriteRepository
import kchaiko.vandrouki.repository.initRetrofit
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import kchaiko.vandrouki.viewmodel.provide.DiscountListViewModel
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module
import ru.terrakok.cicerone.Cicerone

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin(this, listOf(appModule))
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private val appModule = module {
        viewModels()
        repositories()
        cicerone()
        database()
        retrofit()
    }

    private fun ModuleDefinition.viewModels() {
        viewModel { SplashViewModel(get()) }
        viewModel { DiscountListViewModel(get()) }
        viewModel { DiscountViewModel(get(), get()) }
    }

    private fun ModuleDefinition.repositories() {
        single { DiscountRepository(get()) }
        single { FavouriteRepository(get()) }
    }

    private fun ModuleDefinition.database() {
        single { Room.databaseBuilder(androidContext(), VandDatabase::class.java, "vand_db").build() }
    }

    private fun ModuleDefinition.retrofit() {
        single { initRetrofit() }
    }

    private fun ModuleDefinition.cicerone() {
        single { Cicerone.create(VandAppRouter()) }
        factory { get<Cicerone<VandAppRouter>>().navigatorHolder }
        factory { get<Cicerone<VandAppRouter>>().router }
    }

}