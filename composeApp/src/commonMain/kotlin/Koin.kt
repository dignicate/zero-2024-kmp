import com.dignicate.zero_2024_kmp.data.automobile.AutomobileRepositoryImpl
import com.dignicate.zero_2024_kmp.data.sample.SampleApiClient
import com.dignicate.zero_2024_kmp.data.sample.ApiServiceKtorImpl
import com.dignicate.zero_2024_kmp.data.sample.SampleRepositoryImpl
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileRepository
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileUseCase
import com.dignicate.zero_2024_kmp.domain.sample.SampleRepository
import com.dignicate.zero_2024_kmp.domain.sample.SampleUseCase
import com.dignicate.zero_2024_kmp.domain.spla.BukiRepository
import com.dignicate.zero_2024_kmp.ui.automobile.AutomobileCompanyListViewModel
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
    single<SampleApiClient> { ApiServiceKtorImpl(get()) }
}

private val domainModule = module {
    single<SampleRepository> { SampleRepositoryImpl(get()) }
    single<SampleUseCase> { SampleUseCase(get()) }
    single<AutomobileRepository> { AutomobileRepositoryImpl() }
    single<BukiRepository> { BukiRepositoryImpl(get()) }
    single<AutomobileUseCase> { AutomobileUseCase(get()) }
}

private val uiModule = module {
    single<AutomobileCompanyListViewModel> {
        AutomobileCompanyListViewModel()
    }
}
