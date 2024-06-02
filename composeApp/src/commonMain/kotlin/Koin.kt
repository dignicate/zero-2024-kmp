import com.dignicate.zero_2024_kmp.data.sample.ApiService
import com.dignicate.zero_2024_kmp.data.sample.ApiServiceKtorImpl
import com.dignicate.zero_2024_kmp.domain.spla.BukiRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(additionalModules: List<Module> = emptyList()): KoinApplication {
    val koinApplication = startKoin {
        modules(
            additionalModules +
                platformModule +
                domainModule +
                uiModule +
                apiModule +
                ktorModule
        )
    }

    return koinApplication
}

expect val platformModule: Module

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}

val apiModule = module {
    single<ApiService> { ApiServiceKtorImpl(get()) }
}

private val domainModule = module {
    single<BukiRepository> { BukiRepositoryImpl(get()) }
}

private val uiModule = module {
//    single {
//        AddDataViewModel(
//            addDataUseCase = get(),
//        )
//    }
}
