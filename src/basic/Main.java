package basic;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        long sum = 0L;
        long start = System.currentTimeMillis();

        int i = 0;
        while (i < Integer.MAX_VALUE) {
            sum += i;
            i++;
        }

        System.out.println(sum);

        System.out.println(System.currentTimeMillis() - start);
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
