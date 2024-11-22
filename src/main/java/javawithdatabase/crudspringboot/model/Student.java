    package javawithdatabase.crudspringboot.model;



    import jakarta.persistence.*;

    @Entity
    @Table(name = "student")
    public class Student {

        @Id
        private String studentId;

        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String gender;
        private String email;
        private String fatherName;
        private String motherName;
        private String parentPhoneNumber;
        private String address;

        @ManyToOne
        @JoinColumn(name = "level_id")
        private Level level;

        public Student (){
            super();
        }

        public Student(String studentId, String firstName, String lastName, String dateOfBirth, String gender, String email, String fatherName, String motherName, String parentPhoneNumber, String address, Level level) {
            this.studentId = studentId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.email = email;
            this.fatherName = fatherName;
            this.motherName = motherName;
            this.parentPhoneNumber = parentPhoneNumber;
            this.address = address;
            this.level = level;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public String getParentPhoneNumber() {
            return parentPhoneNumber;
        }

        public void setParentPhoneNumber(String parentPhoneNumber) {
            this.parentPhoneNumber = parentPhoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Level getLevel() {
            return level;
        }

        public void setLevel(Level level) {
            this.level = level;
        }
    }
