import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    private static final String TEST_NAME = "testName";
    private static final double TEST_SPEED = 2.0;
    private static final double TEST_DISTANCE = 2.0;
    private static final Horse TEST_HORSE = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
    @Test
    public void constructorNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, TEST_SPEED));
        try {
            new Horse(null, TEST_SPEED);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings= {"", " ", "  ", "\t", "\n"})
    public void constructorEmptyName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED));
        try {
            new Horse(name, TEST_SPEED);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles={-0.1, -5})
    public void constructorNegativeSpeed(double speed) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, speed));
        try {
            new Horse(TEST_NAME, speed);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles={-0.1, -5})
    public void constructorNegativeDistance(double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, TEST_SPEED, distance));
        try {
            new Horse(TEST_NAME, TEST_SPEED, distance);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getNameTest() {
        assertEquals(TEST_NAME, TEST_HORSE.getName());
    }

    @Test
    public void getSpeedTest() {
        assertEquals(TEST_SPEED, TEST_HORSE.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        assertEquals(TEST_DISTANCE, TEST_HORSE.getDistance());
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    public void getRandomDoubleCalled() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            TEST_HORSE.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    public void moveFormulaTest() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            double originalDistance = TEST_HORSE.getDistance();
            TEST_HORSE.move();
            assertEquals(originalDistance + TEST_HORSE.getSpeed() * TEST_HORSE.getRandomDouble(0.2, 0.9), TEST_HORSE.getDistance());
        }
    }


}
