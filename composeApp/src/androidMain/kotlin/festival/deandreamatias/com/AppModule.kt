package festival.deandreamatias.com

import festival.deandreamatias.com.cache.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            ), api = get(),
            assetsDatabase = get()
        )
    }
}