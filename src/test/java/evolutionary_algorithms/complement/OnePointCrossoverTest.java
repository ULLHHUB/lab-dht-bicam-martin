package evolutionary_algorithms.complement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem.definition.Codification;
import problem.definition.Problem;
import problem.definition.State;
import metaheurictics.strategy.Strategy;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OnePointCrossoverTest {

    private OnePointCrossover crossover;

    @BeforeEach
    void setUp() {
        crossover = new OnePointCrossover();
        
        // Setup Strategy singleton with a mock Problem and Codification
        Problem mockProblem = new Problem();
        
        Codification mockCodification = new Codification() {
            @Override
            public boolean validState(State state) { return true; }
            @Override
            public Object getVariableAleatoryValue(int key) { return 0; }
            @Override
            public int getAleatoryKey() { return 0; }
            @Override
            public int getVariableCount() { return 5; } // Length 5
        };
        
        mockProblem.setCodification(mockCodification);
        Strategy.getStrategy().setProblem(mockProblem);
    }

    @Test
    void testCrossover() {
        // Parents with length 5
        State father1 = new State(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        State father2 = new State(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)));
        
        // Probability 1.0 to force crossover
        State child = crossover.crossover(father1, father2, 1.0);
        
        assertNotNull(child);
        assertEquals(5, child.getCode().size());
        
        // Verify child is not null and has valid elements
        boolean has1 = false;
        boolean has2 = false;
        for (Object gene : child.getCode()) {
            if (gene.equals(1)) has1 = true;
            if (gene.equals(2)) has2 = true;
        }
        
        // Child should contain genes from both parents (assuming cut point is not 0 or length-1, or random chance allows it)
        // Since we can't control SecureRandom, we can only assert basic properties.
        // But with length 5, it's highly likely to have both.
        assertTrue(has1 && has2, "Child should contain genes from both parents");
    }
    
    @Test
    void testNoCrossover() {
        State father1 = new State(new ArrayList<>(Arrays.asList(1, 1, 1)));
        State father2 = new State(new ArrayList<>(Arrays.asList(2, 2, 2)));
        
        // Probability 0.0
        State child = crossover.crossover(father1, father2, 0.0);
        
        // Should be a copy of father1
        assertEquals(father1.getCode(), child.getCode());
    }
}
