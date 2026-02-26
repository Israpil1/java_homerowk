package lesson_7.person;

public class Teacher extends Human {
    public String speciality;
    private int experience;

    public Teacher(String lastName, String firstName, int age, String speciality, int experience) {
        super(lastName, firstName, age);
        this.speciality = speciality;
        this.experience = experience;
        System.out.println("TeacherConstructor:\t" + Integer.toHexString(hashCode()));
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return super.toString() + " Teacher{" +
                "speciality='" + speciality + '\'' +
                ", experience=" + experience +
                '}';
    }
}
