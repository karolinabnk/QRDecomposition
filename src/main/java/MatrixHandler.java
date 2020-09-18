import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import java.text.DecimalFormat;


public class MatrixHandler {

        public RealMatrix decompMatrix;
        private String Q;
        private String R;
        public int dim;
        public QRDecomposition decomposition;


        public MatrixHandler(String data, int dim){
            this.decompMatrix = StringToMatrix(data,dim);
            this.dim = dim;
            this.decomposition = new QRDecomposition(decompMatrix);

            this.Q=MatrixToString(decomposition.getQ(),dim);
            this.R=MatrixToString(decomposition.getR(),dim);

        }


        public RealMatrix StringToMatrix(String data, int dim ) {


            String tokens[] = data.split("\\s+");
            double[] ary = new double[tokens.length];

            double MatrixData[][] = new double[dim][dim];


            int i = 0;
            for (String token : tokens) {
                try{
                ary[i++] = Double.parseDouble(token);
                }catch(NumberFormatException e){};

            }

            int j = 0;
            int w = 0;
            while (j < tokens.length) {
                for (int k = j % dim; k < dim; k++) {
                    MatrixData [w][k] = ary[j];
                    j++;
                }
                w++;

            }

            return MatrixUtils.createRealMatrix(MatrixData);


        }

        public String MatrixToString(RealMatrix matrix, int dim) {

            String string = "";
            double[][] matrixData = matrix.getData();
            DecimalFormat df = new DecimalFormat("#.##");

            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim-1; j++) {
                    string += df.format(matrixData[i][j]) + "\t";
                }

            string += df.format(matrixData[i][dim-1])+" \n";
            }



        //        Changing "-0" to "0"
        StringBuilder string2 = new StringBuilder(string);
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == '0')
                if (string.charAt(i + 1) == ' ')
                    if (string.charAt(i - 1) == '-') {
                        string2.setCharAt(i - 1, '0');
                        string2.setCharAt(i, ' ');
                    }
        }

        return string2.toString().trim();
    }


        public String getQ(){
            return this.Q;
        }

        public String getR(){
            return this.R;
        }

}




