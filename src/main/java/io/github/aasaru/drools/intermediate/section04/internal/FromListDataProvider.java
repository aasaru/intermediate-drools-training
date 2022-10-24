package io.github.aasaru.drools.intermediate.section04.internal;

import org.drools.template.DataProvider;

import java.util.List;

public class FromListDataProvider implements DataProvider {

    private List<String[]> data;
    int i;

    public FromListDataProvider(List<String[]> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return i < data.size();
    }

    @Override
    public String[] next() {
        return data.get(i++);
    }
}
