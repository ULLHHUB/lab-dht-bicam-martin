package problem.definition;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import problem.extension.SolutionMethod;
import problem.extension.TypeSolutionMethod;


import factory_interface.IFFactorySolutionMethod;
import factory_method.FactorySolutionMethod;


/**
 * @brief Clase que define un problema de optimización.
 * 
 * Esta clase contiene la información necesaria para definir un problema,
 * incluyendo las funciones objetivo, el tipo de problema (maximizar/minimizar),
 * la codificación, y los operadores disponibles.
 */
public class Problem {

    /**
     * @brief Enumerado para el tipo de optimización.
     */
	public enum ProblemType {Maximizar,Minimizar;}

    /** Lista de funciones objetivo. */
	private ArrayList<ObjetiveFunction> function;
    /** Estado actual o inicial del problema. */
	private State state;
    /** Tipo de problema (Maximizar o Minimizar). */
	private ProblemType typeProblem;
    /** Codificación utilizada para las soluciones. */
	private Codification codification;
    /** Operadores disponibles para el problema. */
	private Operator operator;
    /** Número de valores posibles (para codificaciones discretas). */
	private int possibleValue;
    /** Tipo de método de solución (si aplica). */
	private TypeSolutionMethod typeSolutionMethod;
    /** Fábrica para crear métodos de solución. */
	private IFFactorySolutionMethod factorySolutionMethod;
	
    /**
     * @brief Constructor por defecto.
     */
	public Problem() {
		super();
	}

    /**
     * @brief Obtiene la lista de funciones objetivo.
     * @return ArrayList de ObjetiveFunction.
     */
	public ArrayList<ObjetiveFunction> getFunction() {
		return function;
	}

    /**
     * @brief Establece la lista de funciones objetivo.
     * @param function Nueva lista de funciones objetivo.
     */
	public void setFunction(ArrayList<ObjetiveFunction> function) {
		this.function = function;
	}

    /**
     * @brief Obtiene el estado actual.
     * @return Objeto State.
     */
	public State getState() {
		return state;
	}

    /**
     * @brief Establece el estado actual.
     * @param state Nuevo estado.
     */
	public void setState(State state) {
		this.state = state;
	}

    /**
     * @brief Obtiene el tipo de problema.
     * @return ProblemType (Maximizar o Minimizar).
     */
	public ProblemType getTypeProblem() {
		return typeProblem;
	}

    /**
     * @brief Establece el tipo de problema.
     * @param typeProblem Nuevo tipo de problema.
     */
	public void setTypeProblem(ProblemType typeProblem) {
		this.typeProblem = typeProblem;
	}

    /**
     * @brief Obtiene la codificación del problema.
     * @return Objeto Codification.
     */
	public Codification getCodification() {
		return codification;
	}

    /**
     * @brief Establece la codificación del problema.
     * @param codification Nueva codificación.
     */
	public void setCodification(Codification codification) {
		this.codification = codification;
	}

    /**
     * @brief Obtiene el operador asociado al problema.
     * @return Objeto Operator.
     */
	public Operator getOperator() {
		return operator;
	}

    /**
     * @brief Establece el operador asociado al problema.
     * @param operator Nuevo operador.
     */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

    /**
     * @brief Obtiene el número de valores posibles.
     * @return Entero con el número de valores.
     */
	public int getPossibleValue() {
		return possibleValue;
	}

    /**
     * @brief Establece el número de valores posibles.
     * @param possibleValue Nuevo número de valores.
     */
	public void setPossibleValue(int possibleValue) {
		this.possibleValue = possibleValue;
	}

    /**
     * @brief Evalúa un estado dado.
     * 
     * Calcula el valor de la función objetivo para el estado proporcionado.
     * Si se ha definido un método de solución específico, se utiliza ese método.
     * De lo contrario, se utiliza la primera función objetivo de la lista.
     * 
     * @param state Estado a evaluar.
     * @throws IllegalArgumentException Si hay argumentos inválidos.
     * @throws SecurityException Si hay violación de seguridad en reflexión.
     * @throws ClassNotFoundException Si no se encuentra la clase del método.
     * @throws InstantiationException Si falla la instanciación del método.
     * @throws IllegalAccessException Si no hay acceso al método.
     * @throws InvocationTargetException Si el método invocado lanza una excepción.
     * @throws NoSuchMethodException Si no se encuentra el método.
     */
	public void Evaluate(State state) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		double eval = 0;       
		ArrayList<Double> evaluation = new ArrayList<Double>(this.function.size());
		if (typeSolutionMethod == null) {
			eval= function.get(0).Evaluation(state);
			evaluation.add(evaluation.size(), eval);
			state.setEvaluation(evaluation);
		}
		else {
			SolutionMethod method = newSolutionMethod(typeSolutionMethod);
			method.evaluationState(state);
		}
	}
	
    /**
     * @brief Obtiene el tipo de método de solución.
     * @return TypeSolutionMethod.
     */
	public TypeSolutionMethod getTypeSolutionMethod() {
		return typeSolutionMethod;
	}

    /**
     * @brief Establece el tipo de método de solución.
     * @param typeSolutionMethod Nuevo tipo de método de solución.
     */
	public void setTypeSolutionMethod(TypeSolutionMethod typeSolutionMethod) {
		this.typeSolutionMethod = typeSolutionMethod;
	}

    /**
     * @brief Obtiene la fábrica de métodos de solución.
     * @return IFFactorySolutionMethod.
     */
	public IFFactorySolutionMethod getFactorySolutionMethod() {
		return factorySolutionMethod;
	}

    /**
     * @brief Establece la fábrica de métodos de solución.
     * @param factorySolutionMethod Nueva fábrica.
     */
	public void setFactorySolutionMethod(
			IFFactorySolutionMethod factorySolutionMethod) {
		this.factorySolutionMethod = factorySolutionMethod;
	}
	
    /**
     * @brief Crea una nueva instancia de un método de solución.
     * @param type Tipo de método de solución a crear.
     * @return Instancia de SolutionMethod.
     * @throws IllegalArgumentException Si hay argumentos inválidos.
     * @throws SecurityException Si hay violación de seguridad.
     * @throws ClassNotFoundException Si no se encuentra la clase.
     * @throws InstantiationException Si falla la instanciación.
     * @throws IllegalAccessException Si no hay acceso.
     * @throws InvocationTargetException Si el objetivo de la invocación lanza una excepción.
     * @throws NoSuchMethodException Si no se encuentra el método.
     */
	public SolutionMethod newSolutionMethod(TypeSolutionMethod type) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		factorySolutionMethod = new FactorySolutionMethod();
		return factorySolutionMethod.createdSolutionMethod(type);
	}
}




