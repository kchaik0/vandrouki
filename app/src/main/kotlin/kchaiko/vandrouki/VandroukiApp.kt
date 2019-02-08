package kchaiko.vandrouki

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.facebook.stetho.Stetho
import kchaiko.vandrouki.db.VandDatabase
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.repository.DiscountRepository
import kchaiko.vandrouki.network.repository.FavouriteRepository
import kchaiko.vandrouki.viewmodel.DiscountListViewModel
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
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
        viewModel { DiscountListViewModel(get()) }
        viewModel { (detailUrlPart: String) -> DiscountViewModel(get(), get(), detailUrlPart) }
    }

    private fun ModuleDefinition.repositories() {
        single { DiscountRepository(get()) }
        single { FavouriteRepository(get()) }
    }

    private fun ModuleDefinition.database() {
        single { Room.databaseBuilder(androidContext(), VandDatabase::class.java, "vand_db").build() }
    }

    private fun ModuleDefinition.retrofit() {
        factory { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
        single { RetrofitManager() }
    }

    private fun ModuleDefinition.cicerone() {
        single { Cicerone.create(VandAppRouter()) }
        factory { get<Cicerone<VandAppRouter>>().navigatorHolder }
        factory { get<Cicerone<VandAppRouter>>().router }
    }

}