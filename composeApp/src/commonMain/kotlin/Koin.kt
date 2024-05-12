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
                uiModule
        )
    }

    return koinApplication
}

expect val platformModule: Module

private val domainModule = module {
//    single { AddDataUseCase() }
}

private val uiModule = module {
//    single {
//        AddDataViewModel(
//            addDataUseCase = get(),
//        )
//    }
}
