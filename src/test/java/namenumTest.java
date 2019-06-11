import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class namenumTest {

    @Test
    void isMatched() {
        assertThrows(IllegalArgumentException.class, () -> namenum.isMatched(null, "4734"));
        assertThrows(IllegalArgumentException.class, () -> namenum.isMatched("GREG", null));
        assertTrue(namenum.isMatched("GREG", "4734"));
        assertFalse(namenum.isMatched("GREGE", "4734"));
        assertFalse(namenum.isMatched("GREG", "4735"));
    }
}