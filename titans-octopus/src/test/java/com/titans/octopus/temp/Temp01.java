package com.titans.octopus.temp;

import java.util.HashSet;
import java.util.Set;

/**
 * hashCode相同的两个对象可以同时放进HashSet中吗？
 * @author vinfai
 * @since 2016/8/25
 */
public class Temp01 {

    public static void main(String[] args) {
        User user = new User("name",20,"male1");
        User user1 = new User("name",30,"male11");
        Set<User> set = new HashSet<>();
        set.add(user1);
        set.add(user);
        System.out.println(set.size());

    }
}

class User {

    public User(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    String name;
    Integer age;
    String sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!age.equals(user.age)) return false;
        return sex.equals(user.sex);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        //result = 31 * result + age.hashCode();
        //result = 31 * result + sex.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
