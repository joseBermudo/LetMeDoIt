package cat.copernic.letmedoit.domain.usecases.admin

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Clase para el caso de uso de actualizar el icono de una categoria.
 * @constructor Inyecta una instancia del repositorio de categorías
 * @property categoryRepository el repositorio de categorías para realizar la operación de actualizar el icono de una categoria
 */
class UpdateIconCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    /**
     * Método para invocar el caso de uso de actualizar el icono de una categoria.
     * @param idCategory el id de la categoria a actualizar
     * @param icon el nuevo nombre a actualizar
     * @return un flujo de datos que indica el estado de la operación (success, error, loading) y el resultado de la misma
     */
    suspend operator fun invoke(idCategory: String, icon: String): Flow<DataState<Boolean>> =
        categoryRepository.updateIcon(idCategory, icon)
}