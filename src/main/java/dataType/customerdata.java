package dataType;

public class customerdata
{

    public CustomerSearch customerSearch;
    public Gender gender;

    public class CustomerSearch{

        public String firstname;
        public String lastname;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }


    }

    public class Gender{


        public String select;
        public String experience;

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

    }



}
