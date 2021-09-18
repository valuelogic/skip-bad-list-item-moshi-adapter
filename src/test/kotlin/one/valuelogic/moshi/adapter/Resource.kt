package one.valuelogic.moshi.adapter

import java.io.InputStream
import java.util.Scanner

private const val CHARSET = "UTF-8"
private const val DELIMITER = "\\A"

fun loadFromResources(
    name: String,
    classLoader: ClassLoader
): String = classLoader
    .getResourceAsStream(name)
    .scan(CHARSET, DELIMITER)

private fun InputStream.scan(
    charset: String,
    delimiter: String
): String = Scanner(this, charset)
    .useDelimiter(delimiter)
    .use(Scanner::next)
