package problem.definition;

import problem.definition.Problem.ProblemType;

/**
 * @brief Clase abstracta que define una función objetivo.
 * 
 * Representa una función a optimizar, con su tipo (maximizar/minimizar) y peso asociado.
 */
public abstract class ObjetiveFunction {
	
    /** Tipo de problema para esta función (Maximizar/Minimizar). */
	private ProblemType typeProblem;
    /** Peso de la función objetivo en problemas multiobjetivo. */
	private float weight;
	
    /**
     * @brief Obtiene el peso de la función.
     * @return Valor del peso.
     */
	public float getWeight() {
		return weight;
	}

    /**
     * @brief Establece el peso de la función.
     * @param weight Nuevo peso.
     */
	public void setWeight(float weight) {
		this.weight = weight;
	}

    /**
     * @brief Obtiene el tipo de problema de esta función.
     * @return ProblemType.
     */
	public ProblemType getTypeProblem() {
		return typeProblem;
	}

    /**
     * @brief Establece el tipo de problema de esta función.
     * @param typeProblem Nuevo tipo de problema.
     */
	public void setTypeProblem(ProblemType typeProblem) {
		this.typeProblem = typeProblem;
	}

    /**
     * @brief Evalúa la función objetivo para un estado dado.
     * @param state Estado a evaluar.
     * @return Valor de la evaluación.
     */
	public abstract Double Evaluation(State state);
}
