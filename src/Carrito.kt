// Carrito.kt
data class CartItem(val producto: Producto, var cantidad: Int)

class Carrito {
    // Key = producto.id, value = CartItem (referencia al Producto original)
    private val items = mutableMapOf<Int, CartItem>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (cantidad <= producto.stock) {
            val existing = items[producto.id]
            if (existing != null) {
                existing.cantidad += cantidad
            } else {
                items[producto.id] = CartItem(producto, cantidad)
            }
            producto.stock -= cantidad
            println(" Se agregó $cantidad de ${producto.nombre} al carrito.")
        } else {
            logError("Stock insuficiente para ${producto.nombre}")
            println("Stock insuficiente. Stock actual: ${producto.stock}")
        }
    }


    // Eliminar por índice
    fun eliminarProductoPorIndice(indice: Int) {
        val lista = items.values.toList()
        if (indice < 1 || indice > lista.size) {
            println("Índice no válido.")
            return
        }
        val item = lista[indice - 1]
        item.producto.stock += item.cantidad
        items.remove(item.producto.id)
        println("🗑 Se eliminó ${item.producto.nombre} del carrito. Se restauraron ${item.cantidad} unidades al inventario.")
    }

    // Mostrar carrito (usa la lista de items en items.values)
    fun mostrarCarrito() {
        if (items.isEmpty()) {
            println("El carrito está vacío.")
            return
        }

        var total = 0.0
        println("\n--- Carrito de Compras ---")
        for ((index, item) in items.values.withIndex()) {
            val subtotal = item.cantidad * item.producto.precio
            println("${index + 1}. ${item.producto.nombre} | Cant: ${item.cantidad} | P. Unit: \$${item.producto.precio} | Subtotal: \$${"%.2f".format(subtotal)}")
            total += subtotal
        }
        println("TOTAL: \$${"%.2f".format(total)}\n")
    }

    fun generarFactura() {
        if (items.isEmpty()) {
            println("No hay productos en el carrito.")
            return
        }

        var total = 0.0
        println("\n FACTURA")
        println("------------------------------------------------")
        for (item in items.values) {
            val subtotal = item.cantidad * item.producto.precio
            println("${item.producto.nombre} | Cant: ${item.cantidad} | P. Unit: \$${item.producto.precio} | Subtotal: \$${"%.2f".format(subtotal)}")
            total += subtotal
        }

        val impuesto = total * 0.13  // IVA del 13%
        val totalFinal = total + impuesto
        println("------------------------------------------------")
        println("Subtotal: \$${"%.2f".format(total)}")
        println("IVA (13%): \$${"%.2f".format(impuesto)}")
        println("TOTAL A PAGAR: \$${"%.2f".format(totalFinal)}\n")

        // Vaciar carrito (el stock ya fue ajustado al agregar)
        items.clear()
    }

    // Devuelve una lista copia de los items (para que tienda los muestre)
    fun obtenerItems(): List<CartItem> = items.values.toList()
}
