package hm5;

public class Main {
    static void main(){
        Student s1 = new Student("Roman", "HP", "i7", "16");
        Student s2 = new Student("Vladimir", "HP", "i5", "32");
        System.out.println(s1);
        System.out.println(s2);
    }

    public static class Student {
        String name;
        Laptop laptop;

        public Student(String name, String model, String processor, String storage) {
            this.name = name;
            this.laptop = new Laptop(model, processor, storage);
        }

        // Добавить toString() в Student
        @Override
        public String toString() {
            return name + " => " + laptop;
        }

        private class Laptop {
            String model;
            String processor;
            String storage;

            public Laptop(String model, String processor, String storage) {
                this.model = model;
                this.processor = processor;
                this.storage = storage;
            }

            // Добавить toString() в Laptop
            @Override
            public String toString() {
                return model + ", " + processor + ", " + storage;
            }
        }
    }
}
