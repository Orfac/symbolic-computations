package model;

import java.util.Objects;

public class StringSymbol extends Symbol {
    private String value;

    public StringSymbol(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringSymbol)) return false;

        StringSymbol stringSymbol = (StringSymbol) o;
        return getValue().equals(stringSymbol.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString(){
        return this.value;
    }
}
