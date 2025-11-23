package problem.definition;

/**
 * @brief Clase abstracta que define la codificación de las soluciones.
 * 
 * Define los métodos necesarios para validar estados y generar valores aleatorios
 * para las variables de decisión del problema.
 */
public abstract class Codification {

    /**
     * @brief Valida si un estado es válido según la codificación.
     * @param state Estado a validar.
     * @return true si el estado es válido, false en caso contrario.
     */
	public abstract boolean validState(State state);
//	public abstract int getVariableDomain(int variable);

    /**
     * @brief Obtiene un valor aleatorio para una variable dada.
     * @param key Índice o clave de la variable.
     * @return Objeto que representa el valor de la variable.
     */
	public abstract Object getVariableAleatoryValue(int key);

    /**
     * @brief Obtiene una clave o índice aleatorio de variable.
     * @return Entero con la clave aleatoria.
     */
	public abstract int getAleatoryKey ();

    /**
     * @brief Obtiene el número total de variables.
     * @return Entero con la cantidad de variables.
     */
	public abstract int getVariableCount();

}