import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public String loadPath;
    public String savePath;
    public String matrixData;
    public int dimension;
    public MatrixHandler matrixHandler;


    public FileHandler(String path) throws IOException {
        this.loadPath = path;
        this.savePath = findSavePath(path);
        this.matrixData = LoadMatrix(path);
        this.dimension = findDimension(matrixData);
        this.matrixHandler = new MatrixHandler(matrixData,dimension);

    }


    public String LoadMatrix(String path) throws IOException {

        //     lines from file ---> ArrayList of rows
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        ArrayList<int[]> myArrayList = new ArrayList();
        while ((line = reader.readLine()) != null && line.length() !=0) {
            String[] nums = line.split("\\s+");
            int[] row = new int[nums.length];
            for (int col = 0; col < nums.length; col++) {
                    int n = Integer.parseInt(nums[col]);
                    row[col] = n;
            }

            myArrayList.add(row);

        }
        reader.close();


        //      ArrayList of rows ---> String
        String matrixData = "";
        for (int i = 0; i < myArrayList.size(); i++) {
            int j = 0;
            for (int token : myArrayList.get(i)) {
                matrixData += token + " ";
                j++;
            }
            matrixData += "\n";
        }
        return matrixData;


    }

    public void SaveDecomp(){

        String Q = matrixHandler.getQ();
        String R = matrixHandler.getR();
        File file = new File(savePath);
        String m = matrixData;


        try {

            BufferedReader reader1 = new BufferedReader(new StringReader(Q));
            BufferedReader reader2 = new BufferedReader(new StringReader(R));

            PrintWriter writer = new PrintWriter(new FileWriter(file));

            writer.write("Q factor of the decomposition:");
            writer.println(); writer.println();
            reader1.lines().forEach(line -> writer.println(line));

            writer.println(); writer.println();

            writer.write("R factor of the decomposition: ");
            writer.println(); writer.println();
            reader2.lines().forEach(line -> writer.println(line));


            writer.flush();
            reader1.close();
            reader2.close();

        } catch (IOException e) {}
    }


    public int findDimension(String matrixData) {
        ElementCounter counter = new ElementCounter(matrixData);
        return (int) Math.sqrt(counter.counter);
    }

    public String findSavePath(String path){

        int index = 0;

        for(int i=0;i<path.length();i++) {
            if (path.charAt(i) == '.') index = i;
        }

        return path.substring(0,index) + "_decomposition.txt";
    }

}






