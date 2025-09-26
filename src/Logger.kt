import java.io.File

fun logError(mensaje: String) {
    File("errores.log").appendText("${java.time.LocalDateTime.now()} - ERROR: $mensaje\n")
}
