package kchaiko.vandrouki.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import kchaiko.vandrouki.db.VandDatabase
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.repository.DiscountRepository
import kchaiko.vandrouki.network.repository.FavouriteRepository
import kchaiko.vandrouki.viewmodel.DiscountListViewModel
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.jetbrains.annotations.TestOnly
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module

fun getFullGraph() = listOf(appModule, uiModule)
@TestOnly
fun getAppGraph() = listOf(appModule)

private val appModule = module {
    repositories()
    database()
    retrofit()
}

private val uiModule = module {
    viewModels()
}

private fun ModuleDefinition.viewModels() {
    viewModel { (page: Int) -> DiscountListViewModel(get(), page) }
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