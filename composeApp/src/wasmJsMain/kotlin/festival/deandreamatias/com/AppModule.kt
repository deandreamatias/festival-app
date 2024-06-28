package festival.deandreamatias.com

import festival.deandreamatias.com.alarm.AlarmServiceWasmJs
import festival.deandreamatias.com.cache.WasmDatabaseDriverFactory
import festival.deandreamatias.com.domain.AlarmService
import org.koin.dsl.module

actual val nativeModule = module {
    single<MockSDK> {
        MockSDK(
            databaseDriverFactory = WasmDatabaseDriverFactory(),
            api = get(),
            assetsDatabase = get(),
        )
    }
    single<AlarmService> { AlarmServiceWasmJs() }
}