package ahp_xml;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

/**
 * Created by yevvy on 01/04/2017.
 */
public class ahp_consistency {

    static boolean isAhpMatrixConsistency(Matrix matrix){
        //todo: Implement
        return true;
    }

    public boolean checkConsistency(Matrix matrix) {

        int n = matrix.getColumnDimension();

        if (n >= 3) {
            double consistencyRatio = consistencyRatio(matrix);

            if (consistencyRatio <= 0.1) {
                return true;
            } else {
                return false;
            }
        } else if (n == 1 || n==2 ) {
            return true;
        } else {
            return false;
        }

    }

    public double consistencyIndex(Matrix matrix) {

        double maxEingenvalue = findMaxEingenvalue(matrix);
        int n = matrix.getColumnDimension();
        double consistencyIndex = (maxEingenvalue - n) / (n - 1);
        return consistencyIndex;
    }

    public double consistencyRatio(Matrix matrix) {

        double[] RI = { 0, 0, 0, 0.5247, 0.8816, 1.1086, 1.2479, 1.3417, 1.4057, 1.4499, 1.4854 };

        int n = matrix.getColumnDimension();
        double consistencyIndex = consistencyIndex(matrix);
        double consistencyRatio = consistencyIndex / RI[n];
        return consistencyRatio;
    }

    public double findMaxEingenvalue(Matrix matrix) {

        EigenvalueDecomposition eigenvalues = matrix.eig();

        double[] real = eigenvalues.getRealEigenvalues();

        double maxEigenvalue = Math.abs(real[0]);
        int size = matrix.getColumnDimension();

        for (int i = 0; i < real.length; i++) {
            real[i] = Math.abs(real[i]);
            if (real[i] > maxEigenvalue) {
                maxEigenvalue = real[i];
            }
        }

        return maxEigenvalue;

    }
}
