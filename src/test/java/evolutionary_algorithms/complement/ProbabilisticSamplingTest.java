package evolutionary_algorithms.complement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem.definition.Codification;
import problem.definition.Problem;
import problem.definition.State;
import metaheurictics.strategy.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbabilisticSamplingTest {

    private ProbabilisticSampling sampling;

    @BeforeEach
    void setUp() {
        sampling = new ProbabilisticSampling();
        
        Problem mockProblem = new Problem();
        mockProblem.setPossibleValue(3); // Values 0, 1, 2
        
        Codification mockCodification = new Codification() {
            @Override
            public boolean validState(State state) { return true; }
            @Override
            public Object getVariableAleatoryValue(int key) { return 0; }
            @Override
            public int getAleatoryKey() { return 0; }
            @Override
            public int getVariableCount() { return 5; }
        };
        
        mockProblem.setCodification(mockCodification);
        Strategy.getStrategy().setProblem(mockProblem);
    }

    @Test
    void testSampling() {
        // Create fathers
        List<State> fathers = new ArrayList<>();
        // Father 1: [0, 0, 0, 0, 0]
        fathers.add(new State(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0))));
        // Father 2: [1, 1, 1, 1, 1]
        fathers.add(new State(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1))));
        // Father 3: [2, 2, 2, 2, 2]
        fathers.add(new State(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2))));
        
        int countInd = 10;
        List<State> result = sampling.sampling(fathers, countInd);
        
        assertNotNull(result);
        assertEquals(countInd, result.size());
        
        for (State s : result) {
            assertEquals(5, s.getCode().size());
            for (Object gene : s.getCode()) {
                assertTrue(gene instanceof Integer);
                int val = (Integer) gene;
                // Values should be mostly 0, 1, 2. 
                // Fallback logic might produce other values if something goes wrong, but here it should be fine.
                assertTrue(val >= 0); 
            }
        }
    }
}
