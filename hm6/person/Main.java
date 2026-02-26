package lesson_7.person;

public class Main {
    public static void main(String[] args) {
        Human human = new Human("Батодалаев", "Даши", 16);
        System.out.println(human);

        Student student = new Student("Загидуллин", "Линар", 32, "РПО", "PD_011", 5);
        System.out.println(student);

        Teacher teacher = new Teacher("Даньшин", "Андрей", 38, "Астрофизика", 110);
        System.out.println(teacher);

        Graduate graduate = new Graduate("Шугани", "Сергей", 15, "РПО", "PD_011", 5, "Защита персональных данных");
        System.out.println(graduate);

        Student student1 = new Student(human, "ГК", "Web_011", 5);
        System.out.println(student1);

        Graduate graduate1 = new Graduate(student, "Математические вычисления");
        System.out.println(graduate1);

        Specialist spec1 = new Specialist("Иванов", "Иван", 56, "механика", 30, 4, student);
        System.out.println(spec1);
    }
}
