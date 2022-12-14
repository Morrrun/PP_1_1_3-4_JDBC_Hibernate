package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && lastName.equals(user.lastName) && age.equals(user.age);
    }

    @Override
    public int hashCode() {
        int res = id.hashCode();
        res += name.hashCode();
        res += lastName.hashCode();
        res += age.hashCode();
        res = 31 * res + (id.hashCode() * name.hashCode() * lastName.hashCode() * age.hashCode());
        return res;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-20s \n    %-9s %-20d \n    %-9s %-20d\n",
                "Пользователь:", name + " " + lastName, "Возраст", age, "id", id);
    }
}
