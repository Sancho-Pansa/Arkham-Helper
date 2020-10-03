package arkham.json;

import arkham.mechanics.AncientOne;
import arkham.mechanics.AncientTypes;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class AncientOneFromJsonTest {

    @Test
    public void convertAncientOne() {
        AncientOne ao = AncientOneFromJson.getAncientOne();
        assertEquals("Хастур", ao.getName());
    }
}