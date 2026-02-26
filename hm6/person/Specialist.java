package lesson_7.person;

public class Specialist extends Teacher {
    private int rate; // Оценка, которую ставит специалист
    private Student student; // Студент, которого оценивают

    // Способ 1: Создание специалиста с нуля
    public Specialist(String lastName, String firstName, int age, String speciality, int experience, int rate, Student student) {
        super(lastName, firstName, age, speciality, experience); // Вызов конструктора Teacher
        this.rate = rate;
        this.student = student;
        System.out.println("SpecialistConstructor:\t" + Integer.toHexString(hashCode()));
    }

    // Геттеры и сеттеры
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return super.toString() + " Specialist{" +
                "оценивает_студента=" + student.getLastName() + // Берем фамилию из объекта
                ", оценка=" + rate +
                '}';
    }
}