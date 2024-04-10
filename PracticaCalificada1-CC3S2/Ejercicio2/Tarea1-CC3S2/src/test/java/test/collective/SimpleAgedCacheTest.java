package test.collective;

import io.collective.SimpleAgedCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleAgedCacheTest {
    SimpleAgedCache empty = new SimpleAgedCache();
    SimpleAgedCache nonempty = new SimpleAgedCache();

    @BeforeEach
    public void before() {          // la llena con dos filas ?
        nonempty.put("aKey", "aValue", 2000); // sin llaves , sin valor , 20000
        nonempty.put("anotherKey", "anotherValue", 4000);   // con llaves, con valor , 40000
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());              //  Verifica si esta vacia
        assertFalse(nonempty.isEmpty());            // noempty verifica que no este vacia
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());              // revisa que tenga tamaño cero ya que esta vacia
        assertEquals(2, nonempty.size());           // revisa que tenga tamaño dos
    }

    @Test
    public void get() {
        assertNull(empty.get("aKey"));                  //Verifica que regrese nulo ya que "empty" debe estar vacio
        assertEquals("aValue", nonempty.get("aKey"));       // verifica que "akey" corresponda "avalue
        assertEquals("anotherValue", nonempty.get("anotherKey"));  // "anotherValue --- "Another key"
    }

    @Test
    public void getExpired() {
        TestClock clock = new TestClock();

        SimpleAgedCache expired = new SimpleAgedCache(clock);
        expired.put("aKey", "aValue", 2000);
        expired.put("anotherKey", "anotherValue", 4000);

        clock.offset(Duration.ofMillis(3000));

        assertEquals(1, expired.size());
        assertEquals("anotherValue", expired.get("anotherKey"));
    }

    static class TestClock extends Clock {
        Duration offset = Duration.ZERO;

        @Override
        public ZoneId getZone() {
            return Clock.systemDefaultZone().getZone();
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return Clock.offset(Clock.system(zone), offset);
        }

        @Override
        public Instant instant() {
            return Clock.offset(Clock.systemDefaultZone(), offset).instant();
        }

        public void offset(Duration offset) {
            this.offset = offset;
        }
    }
}
