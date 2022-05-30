package com.github.inclutab;

import java.io.*;

public class QuestionFive {
    private static GraphImpl graph;


    public static void main(String[] args) {
        try {
            loadGraph("C:/dev/servByteAlgorithmTest/teradata-world-setup.txt");
            loadInputFromFile("C:/dev/servByteAlgorithmTest/client-request-1.txt");
//            loadInputFromFile("C:/dev/servByteAlgorithmTest/client-request-2.txt");
//            loadInputFromFile("C:/dev/servByteAlgorithmTest/client-request-3.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadGraph(String fileUrl) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileUrl))) {
            int numberOfVertices = Integer.parseInt(reader.readLine());
            graph = new GraphImpl(numberOfVertices);
            reader.lines().forEach(line->{
                String[] array = line.split(",");
                int parent = Integer.parseInt(array[0]);
                int child = Integer.parseInt(array[1]);
                double probabilityOfTransmission = Double.parseDouble(array[2])/100;
                graph.addEdge(parent, child, probabilityOfTransmission);
            });
        }
        catch (IOException ex){
            throw new FileNotFoundException("Cannot read from tera-world-setup-file");
        }
    }

    private static String readFromFileAndReturnString(BufferedReader readerForClientRequest) throws IOException, InvalidIndexException {
        StringBuilder output = new StringBuilder();
        String line;
        while (!(line = readerForClientRequest.readLine()).equalsIgnoreCase("END")){
            line = line.trim();
            if (!line.equals("") && !line.equalsIgnoreCase("END")){
                String[] splitLine = line.split(",");
                int source = Integer.parseInt(splitLine[0]);
                int destination = Integer.parseInt(splitLine[1]);
                double thresholdProbability = Double.parseDouble(splitLine[2]);
                double probabilityOfTransmission = graph.traverseGraphAndReturnProbability(source-1,destination-1);
                System.out.printf("source: %d destination: %d probability: %f %n)", source, destination, probabilityOfTransmission);
                if (probabilityOfTransmission >= thresholdProbability){
                    output.append("YES").append("\n");
                }
                else{
                    output.append("NO").append("\n");
                }
            }
        }
        return output.toString();
    }


    private static void writeToOutputFileThree(String fileUrl, String output) throws IOException {
        BufferedWriter outWriter = new BufferedWriter(new FileWriter("C:/dev/servByteAlgorithmTest/client-response-1.txt"));
        outWriter.write(output);
        outWriter.flush();
        System.out.println("Successful output for this response!");
    }

    private static void loadInputFromFile(String fileUrl) {
        try( BufferedReader readerForClientRequest =
                     new BufferedReader(new FileReader(fileUrl))){
            String output = readFromFileAndReturnString(readerForClientRequest);
            writeToOutputFileThree(fileUrl, output);
        }
        catch (IOException | InvalidIndexException e) {
            e.printStackTrace();
        }
    }
}
