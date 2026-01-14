package io.sanchopansa.arkham.factories;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathJsonSource implements JsonSource {
    private final ClassLoader classLoader;

    public ClasspathJsonSource(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public InputStream openGameData(String filename) throws IOException {
        InputStream is = classLoader.getResourceAsStream(filename);
        if (is == null) {
            throw new IOException("Resource not found: " + filename);
        }
        return is;
    }
}
