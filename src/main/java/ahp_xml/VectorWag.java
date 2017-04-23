package ahp_xml;

import java.util.Arrays;

/**
 * Created by EwaStachow on 15/03/2017.
 */

public class VectorWag {

    double[] vector;
    double wag;

    VectorWag(double[] vector, double wag) {
        this.vector = vector;
        this.wag = wag;
    }

    VectorWag() {
    }

    @Override
    public String toString() {
        return "VectorWag{" +
                "vector=" + Arrays.toString(vector) +
                ", wag=" + wag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VectorWag vectorWag = (VectorWag) o;

        if (Double.compare(vectorWag.wag, wag) != 0) return false;
        return Arrays.equals(vector, vectorWag.vector);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Arrays.hashCode(vector);
        temp = Double.doubleToLongBits(wag);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
