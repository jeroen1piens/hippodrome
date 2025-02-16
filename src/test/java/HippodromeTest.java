import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class HippodromeTest {

    Horse MOCK_HORSE = Mockito.mock(Horse.class);

    @Test
    public void constructorNullHorses() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void constructorEmptyHorses() {
        List<Horse> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        try {
            new Hippodrome(emptyList);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    public void moveTest() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(MOCK_HORSE);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(MOCK_HORSE,times(50)).move();
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(new Horse("Test " + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(49), hippodrome.getWinner());
    }


}
