package cat.copernic.letmedoit.Utils

/**
 * Sealed Class que representa estados de una petición --> éxito, error, carga y finalización.
 * Interfaz que nos permite representar jerarquias restringidas donde un objeto solo puede ser de un tipo de las dadas.
 * Muy util para la programación funcional --> permite cocnatenar funciones en una sola instrucción.
 * Básicamente es como un enum. Permite tener una clase con un número especifico de clases.
 * Más información --> https://devexperto.com/sealed-classes-kotlin/#:~:text=Una%20clase%20sellada%20(o%20sealed,similar%20al%20de%20un%20enumerado.
 */

sealed class DataState <out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception : Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Finished : DataState<Nothing>()
}