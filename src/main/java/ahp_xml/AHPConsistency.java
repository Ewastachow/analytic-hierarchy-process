package ahp_xml;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

/**
 * Created by yevvy on 01/04/2017.
 */
public class AHPConsistency {

    public static boolean checkConsistency(Matrix matrix, double ratio) {
        int size = matrix.getColumnDimension();
        return (size == 1) || (size == 2) || ((size > 2) && (consistencyRatio(matrix) <= ratio));
    }

    private static double consistencyRatio(Matrix matrix) {
        double[] RI = { 0, 0, 0, 0.5247, 0.8816, 1.1086, 1.2479, 1.3417, 1.4057, 1.4499, 1.4854 };
        return (consistencyIndex(matrix)/RI[matrix.getColumnDimension()]);
    }

    private static double consistencyIndex(Matrix matrix) {
        int size = matrix.getColumnDimension();
        return ((findMaxEingenvalue(matrix)-size)/(size-1));
    }

    private static double findMaxEingenvalue(Matrix matrix) {
        EigenvalueDecomposition eigenvalues = matrix.eig();
        double[] real = eigenvalues.getRealEigenvalues();
        double maxEigenvalue = Math.abs(real[0]);
        for (int i = 0; i < real.length; i++) {
            real[i] = Math.abs(real[i]);
            if (real[i] > maxEigenvalue)
                maxEigenvalue = real[i];
        }
        return maxEigenvalue;
    }
}
