package model.log;

import java.util.Collections;
import java.util.List;

public class DummyLog extends Log {
    public DummyLog() {};

    @Override
    public void log(String msg) {}

    @Override
    public List<String> getItems() {
        return Collections.emptyList();
    }
}
