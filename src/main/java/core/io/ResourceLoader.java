package core.io;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
