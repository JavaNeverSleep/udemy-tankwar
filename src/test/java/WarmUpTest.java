import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarmUpTest {

    @Test
    void findMaxAndMin() {
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findMaxAndMin(null));
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findMaxAndMin(new int[]{}));
        assertEquals(new Range(1, 5), WarmUp.findMaxAndMin(new int[]{1, 2, 3, 4, 5}));
        assertEquals(new Range(1, 1), WarmUp.findMaxAndMin(new int[]{1}));
        assertEquals(new Range(1, 1), WarmUp.findMaxAndMin(new int[]{1, 1, 1, 1}));
    }

    @Test
    void findThreeSumsIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findThreeSums(null));
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findThreeSums(new int[]{}));
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findThreeSums(new int[]{0, 1}));
        assertThrows(IllegalArgumentException.class, () -> WarmUp.findThreeSums(new int[]{0, 0, 0, 0}));
    }

    @Test
    void findThreeSums() {
        assertEquals(2, WarmUp.findThreeSums(new int[]{-1, -2, 3, -4, -5, 9, 10, 11}));
    }

}