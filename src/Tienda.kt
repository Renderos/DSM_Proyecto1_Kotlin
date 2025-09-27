// Tienda.kt
import java.util.Scanner

class Tienda {
    private val productos = mutableListOf(
        Producto(1, "Laptop", 750.0, 5),
        Producto(2, "Mouse", 25.0, 10),
        Producto(3, "Teclado", 40.0, 7),
        Producto(4, "Monitor", 150.0, 4)
    )

    val carrito = Carrito()

    fun mostrarProductos() {
        println("\n--- Productos Disponibles ---")
        for (p in productos) {
            println("${p.id}. ${p.nombre} | Precio: \$${p.precio} | Stock: ${p.stock}")
        }
    }

    fun seleccionarProducto(scanner: Scanner) {
        mostrarProductos()
        print("Ingrese el ID del producto a comprar: ")
        val id = scanner.nextInt()
        val producto = productos.find { it.id == id }

        if (producto != null) {
            print("Ingrese la cantidad: ")
            val cantidad = scanner.nextInt()
            carrito.agregarProducto(producto, cantidad)
        } else {
            println("Producto no encontrado")
        }
    }

    // Muestra lo que hay en el carrito (numerado) y elimina por índice
    fun eliminarProducto(scanner: Scanner) {
        val items = carrito.obtenerItems()

        if (items.isEmpty()) {
            println("⚠ No hay productos en el carrito para eliminar.")
            return
        }

        println("\n--- Productos en el Carrito ---")
        for ((index, item) in items.withIndex()) {
            println("${index + 1}. ${item.producto.nombre} | Cant: ${item.cantidad} | P. Unit: \$${item.producto.precio}")
        }

        print("Seleccione el número del producto a eliminar (o 0 para cancelar): ")
        val opcion = scanner.nextInt()

        if (opcion == 0) {
            println("Operación cancelada.")
            return
        }

        if (opcion in 1..items.size) {
            // Llamamos a eliminar por índice en Carrito (restaura el stock internamente)
            carrito.eliminarProductoPorIndice(opcion)
        } else {
            println("⚠ Opción no válida.")
        }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

}
