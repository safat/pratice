package basic;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader eOutputReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/muhossain/Downloads/output.txt")));
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/muhossain/Downloads/input.txt")));
        inputReader.readLine();
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(new FileInputStream("output.txt")));

        while (true) {
            String input = inputReader.readLine();
            String expectedOutput = eOutputReader.readLine();
            String output = outputReader.readLine();

            if (expectedOutput == null || output == null) {
                break;
            }

            if (!expectedOutput.equals(output)) {
                System.out.println("expected: " + expectedOutput + " found: " + output + " when input: " + input);
            }
        }
    }

    static class Student {
        private int marks;
        private String studentName;

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public Student(String studentName, int marks) {
            this.marks = marks;
            this.studentName = studentName;
        }
    }
}
