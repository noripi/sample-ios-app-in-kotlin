package extensions

/**
 * It should be defined in Kotlin1.1 but somehow it cannot be found in Kotlin/Native 0.3;
 * Delete this function when it is defined
 */
inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? = if (predicate(this)) this else null