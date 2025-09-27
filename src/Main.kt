import java.util.Scanner

// Main.kt (resumen)
fun main() {
    val tienda = Tienda()
    val scanner = Scanner(System.`in`)
    var opcion: Int
    do {
        println("\n===== MENÚ PRINCIPAL =====")
        println("1. Ver productos")
        println("2. Agregar producto al carrito")
        println("3. Eliminar producto del carrito")
        println("4. Ver carrito")
        println("5. Generar factura")
        println("0. Salir")
        print("Seleccione una opción: ")

        opcion = scanner.nextInt()
        when (opcion) {
            1 -> tienda.mostrarProductos()
            2 -> tienda.seleccionarProducto(scanner)
            3 -> tienda.eliminarProducto(scanner)
            4 -> tienda.carrito.mostrarCarrito()
            5 -> tienda.carrito.generarFactura()
            0 -> println(" Gracias por su compra. ¡Vuelva pronto!")
            else -> println("⚠ Opción no válida.")
        }
    } while (opcion != 0)
}
