interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

fun Platform.isIos(): Boolean = name == "iOS"
