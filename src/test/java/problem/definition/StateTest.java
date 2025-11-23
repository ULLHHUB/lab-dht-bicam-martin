package problem.definition;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void testConstructorsAndGetters() {
        ArrayList<Object> code = new ArrayList<>(Arrays.asList(1, 2, 3));
        State state = new State(code);
        
        assertEquals(code, state.getCode());
        
        State emptyState = new State();
        assertNotNull(emptyState.getCode());
        assertTrue(emptyState.getCode().isEmpty());
    }

    @Test
    void testSetters() {
        State state = new State();
        ArrayList<Object> code = new ArrayList<>(Arrays.asList("A", "B"));
        state.setCode(code);
        assertEquals(code, state.getCode());
        
        ArrayList<Double> eval = new ArrayList<>(Arrays.asList(1.0, 2.0));
        state.setEvaluation(eval);
        assertEquals(eval, state.getEvaluation());
        
        state.setNumber(5);
        assertEquals(5, state.getNumber());
    }

    @Test
    void testGetCopy() {
        ArrayList<Object> code = new ArrayList<>(Arrays.asList(1, 2, 3));
        State state = new State(code);
        State copy = (State) state.getCopy();
        
        assertNotSame(state, copy);
        assertEquals(state.getCode(), copy.getCode());
        // Note: Current implementation of getCopy shares the list reference via constructor
        assertSame(state.getCode(), copy.getCode()); 
    }
    
    @Test
    void testCopyConstructor() {
        ArrayList<Object> code = new ArrayList<>(Arrays.asList(1, 2, 3));
        State state = new State(code);
        state.setNumber(10);
        
        State copy = new State(state);
        
        assertEquals(state.getNumber(), copy.getNumber());
        assertEquals(state.getCode(), copy.getCode());
        // Copy constructor creates a new ArrayList
        assertNotSame(state.getCode(), copy.getCode());
    }

    @Test
    void testComparator() {
        State s1 = new State(new ArrayList<>(Arrays.asList(1, 2)));
        State s2 = new State(new ArrayList<>(Arrays.asList(1, 2)));
        State s3 = new State(new ArrayList<>(Arrays.asList(1, 3)));
        
        assertTrue(s1.Comparator(s2));
        assertFalse(s1.Comparator(s3));
    }
    
    @Test
    void testDistance() {
        State s1 = new State(new ArrayList<>(Arrays.asList(1, 2, 3)));
        State s2 = new State(new ArrayList<>(Arrays.asList(1, 0, 3))); // 1 diff
        
        assertEquals(1.0, s1.Distance(s2));
    }
}
