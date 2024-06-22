package festival.deandreamatias.com

import festival.deandreamatias.com.cache.IOSDatabaseDriverFactory
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = IOSDatabaseDriverFactory(), api = get()
        )
    }
}