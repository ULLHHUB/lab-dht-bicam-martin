package problem.definition;



import java.util.ArrayList;

import metaheuristics.generators.GeneratorType;

/**
 * @brief Clase que representa un estado o solución en el problema.
 * 
 * Esta clase encapsula la información de una solución candidata, incluyendo su codificación,
 * evaluación, y el generador que la produjo.
 */
public class State {
	
    /** Tipo de generador que creó este estado. */
	protected GeneratorType typeGenerator;
    /** Lista de valores de evaluación (objetivos). */
	protected ArrayList<Double> evaluation;
    /** Identificador numérico del estado. */
	protected int number;
    /** Codificación de la solución (variables). */
	protected ArrayList<Object> code;
	
    /**
     * @brief Constructor de copia.
     * @param ps Estado a copiar.
     */
	public State(State ps) {
		typeGenerator = ps.getTypeGenerator();
		evaluation = ps.getEvaluation();
		number = ps.getNumber();
		code = new ArrayList<Object>(ps.getCode());
	}
	
    /**
     * @brief Constructor con código inicial.
     * @param code Lista de objetos que representa la codificación de la solución.
     */
	public State(ArrayList<Object> code) {
		super();
		this.code = code;
	}
	
    /**
     * @brief Constructor por defecto.
     * Inicializa la lista de código vacía.
     */
	public State() {
		code=new ArrayList<Object>();
	}	
	
    /**
     * @brief Obtiene la codificación del estado.
     * @return ArrayList con los objetos de la codificación.
     */
	public ArrayList<Object> getCode() {
		return code;
	}

    /**
     * @brief Establece la codificación del estado.
     * @param listCode Nueva lista de codificación.
     */
	public void setCode(ArrayList<Object> listCode) {
		this.code = listCode;
	}

    /**
     * @brief Obtiene el tipo de generador.
     * @return Tipo de generador.
     */
	public GeneratorType getTypeGenerator() {
		return typeGenerator;
	}

    /**
     * @brief Establece el tipo de generador.
     * @param typeGenerator Nuevo tipo de generador.
     */
	public void setTypeGenerator(GeneratorType typeGenerator) {
		this.typeGenerator = typeGenerator;
	}

	
    /**
     * @brief Obtiene la evaluación del estado.
     * @return Lista de valores de evaluación.
     */
	public ArrayList<Double> getEvaluation() {
		return evaluation;
	}

    /**
     * @brief Establece la evaluación del estado.
     * @param evaluation Nueva lista de evaluación.
     */
	public void setEvaluation(ArrayList<Double> evaluation) {
		this.evaluation = evaluation;
	}

    /**
     * @brief Obtiene el número identificador del estado.
     * @return Número del estado.
     */
	public int getNumber() {
		return number;
	}

    /**
     * @brief Establece el número identificador del estado.
     * @param number Nuevo número.
     */
	public void setNumber(int number) {
		this.number = number;
	}

    /**
     * @brief Clona el estado actual.
     * @return El mismo objeto estado (Nota: Implementación actual devuelve this).
     */
	public State clone(){
		return this;
	}
	
    /**
     * @brief Crea una copia profunda del estado (solo código).
     * @return Nuevo objeto State con el mismo código.
     */
	public Object getCopy(){
		return new State(this.getCode());
	}
	
    /**
     * @brief Compara este estado con otro.
     * @param state Estado a comparar.
     * @return true si los códigos son iguales, false en caso contrario.
     */
	public boolean Comparator(State state){

		boolean result=false;
		if(state.getCode().equals(getCode())){
			result=true;
		}
		return result;
	}

    /**
     * @brief Calcula la distancia de Hamming entre este estado y otro.
     * @param state Estado con el cual calcular la distancia.
     * @return Número de posiciones en las que difieren los códigos.
     */
	public double Distance(State state){
		double distancia = 0;
		for (int i = 0; i < state.getCode().size(); i++) {
			if (!(state.getCode().get(i).equals(this.getCode().get(i)))) {
				distancia++;
			}
		}
	return distancia;
	}
}
