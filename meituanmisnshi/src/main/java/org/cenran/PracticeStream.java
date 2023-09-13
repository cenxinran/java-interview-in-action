package org.cenran;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-11  00:08
 * @Description: TODO
 * @Version: 1.0
 */
public class PracticeStream {

    public static void main(String[] args) {

        ArrayList<String> manList = new ArrayList<>();

        manList.add("周润发");
        manList.add("成龙");
        manList.add("刘德华");
        manList.add("吴京");
        manList.add("周星驰");
        manList.add("李连杰");

        ArrayList<String> womanList = new ArrayList<>();

        womanList.add("林心如");
        womanList.add("张曼玉");
        womanList.add("林青霞");
        womanList.add("柳岩");
        womanList.add("林志玲");
        womanList.add("王祖贤");

        Stream<String> manStream = manList.stream().filter(s -> s.length() == 3).limit(3);
        Stream<String> womanStream = womanList.stream().filter(s -> s.startsWith("林")).skip(1);
        Stream<String> concat = Stream.concat(manStream, womanStream);
        //concat.forEach(s -> {
        //    Actor actor = new Actor(s);
        //    System.out.println(actor.getName());
        //});
        concat.map(Actor::new).forEach(s -> System.out.println(s.getName()));
    }

    public static class Actor {
        public String name;

        public Actor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
