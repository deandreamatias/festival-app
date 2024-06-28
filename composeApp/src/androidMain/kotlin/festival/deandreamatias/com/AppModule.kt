package festival.deandreamatias.com

import festival.deandreamatias.com.alarm.AlarmServiceAndroid
import festival.deandreamatias.com.cache.AndroidDatabaseDriverFactory
import festival.deandreamatias.com.domain.AlarmService
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
    single<AlarmService> { AlarmServiceAndroid(androidContext()) }

}