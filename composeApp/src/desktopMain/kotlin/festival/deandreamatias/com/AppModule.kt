package festival.deandreamatias.com

import festival.deandreamatias.com.alarm.AlarmServiceDesktop
import festival.deandreamatias.com.cache.DesktopDatabaseDriverFactory
import festival.deandreamatias.com.domain.AlarmService
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = DesktopDatabaseDriverFactory(), api = get(),
            assetsDatabase = get()
        )
    }
    single<AlarmService> { AlarmServiceDesktop() }
}