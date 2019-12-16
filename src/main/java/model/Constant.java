package model;

import java.util.Objects;

public class Constant extends Symbol {
    private double value;

    public Constant(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Constant)) return false;

        Constant constant = (Constant) o;
        return Double.compare(constant.getValue(), getValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }
}
