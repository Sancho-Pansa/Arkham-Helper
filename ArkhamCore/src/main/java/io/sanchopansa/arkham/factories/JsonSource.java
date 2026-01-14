package io.sanchopansa.arkham.factories;

import java.io.IOException;
import java.io.InputStream;

public interface JsonSource {
    InputStream openGameData(String filename) throws IOException;
}
