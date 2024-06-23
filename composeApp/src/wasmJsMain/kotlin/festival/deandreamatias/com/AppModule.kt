//package festival.deandreamatias.com
//
//import festival.deandreamatias.com.cache.WasmDatabaseDriverFactory
//import org.koin.dsl.module
//
//actual val nativeModule = module {
//    single<MockSDK> {
//        MockSDK(
//            databaseDriverFactory = WasmDatabaseDriverFactory(), api = get()
//        )
//    }
//}