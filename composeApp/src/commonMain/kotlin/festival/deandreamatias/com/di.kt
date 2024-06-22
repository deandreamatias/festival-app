package festival.deandreamatias.com

import festival.deandreamatias.com.network.MockApi
import festival.deandreamatias.com.ui.ShowsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.startKoin

val viewModelsModule = module {
    viewModelOf(::ShowsViewModel)
}

val appModule = module {
    single<MockApi> { MockApi() }
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, viewModelsModule, nativeModule)
    }
}