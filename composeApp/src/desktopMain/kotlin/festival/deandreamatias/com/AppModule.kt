package festival.deandreamatias.com

import festival.deandreamatias.com.cache.DesktopDatabaseDriverFactory
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = DesktopDatabaseDriverFactory(), api = get(),
            assetsDatabase = get()
        )
    }
}