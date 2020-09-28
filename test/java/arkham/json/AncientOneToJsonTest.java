package arkham.json;

import arkham.mechanics.AncientOne;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class AncientOneToJsonTest {

    @Test
    public void convertAncientOne() {
        AncientOne ao = new AncientOne(
                "Хастур",
                12,
                new HashSet<>(Arrays.asList(AncientOne.AncientTypes.MAP_RELATED)));
        assertTrue(AncientOneToJson.convertAncientOne(ao));
    }
}