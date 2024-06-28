package festival.deandreamatias.com

import festival.deandreamatias.com.alarm.AlarmServiceIos
import festival.deandreamatias.com.cache.IOSDatabaseDriverFactory
import festival.deandreamatias.com.domain.AlarmService
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = IOSDatabaseDriverFactory(), api = get(),
            assetsDatabase = get()
        )
    }
    single<AlarmService> { AlarmServiceIos() }
}