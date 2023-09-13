package org.cenran;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @BelongsProject: java-interview-in-action
 * @BelongsPackage: org.cenran
 * @Author: 岑然
 * @CreateTime: 2023-09-11  00:34
 * @Description: 美团二面之前的题目准备-stream流
 * @Version: 1.0
 */
public class PracticeStream_02 {

    public static void main(String[] args) {

        ArrayList<ShangHu> shangHus = new ArrayList<>();
        ArrayList<String> shangHu01 = new ArrayList<>();
        shangHu01.add("A1");
        shangHu01.add("B1");
        shangHu01.add("C");
        ArrayList<String> shangHu02 = new ArrayList<>();
        shangHu01.add("D2");
        shangHu01.add("B");
        shangHu01.add("C");
        ArrayList<String> shangHu03 = new ArrayList<>();
        shangHu01.add("E3");
        shangHu01.add("B");
        shangHu01.add("C");
        ArrayList<String> shangHu04 = new ArrayList<>();
        shangHu01.add("A4");
        shangHu01.add("B");
        shangHu01.add("C");
        shangHus.add(new ShangHu("1", "商户 A", "5", shangHu01, 2));
        shangHus.add(new ShangHu("2", "商户 B", "3", shangHu02, 21));
        shangHus.add(new ShangHu("3", "商户 C", "4", shangHu03, 20));
        shangHus.add(new ShangHu("4", "商户 D", "2", shangHu04, 9));

        // 1. 生成 ID 为 key ，名称为 value 的 map
        Map<String, String> map = shangHus.stream().collect(Collectors.toMap(ShangHu::getId, ShangHu::getName));
        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " --- " + entry.getValue()));
        // 2. 获取所有评分大于 4.5 ，菜品包含 A B 两类的所有商户的评价数
        //shangHus.stream().filter();

    }


    public static class ShangHu {
        public String id;
        public String name;
        public List<String> caiPin;
        public String score;
        public int pingJiaShu;

        public ShangHu(String id, String name, String score, List<String> caiPin, int pingJiaShu) {
            this.id = id;
            this.name = name;
            this.caiPin = caiPin;
            this.score = score;
            this.pingJiaShu = pingJiaShu;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCaiPin() {
            return caiPin;
        }

        public void setCaiPin(List<String> caiPin) {
            this.caiPin = caiPin;
        }

        public int getPingJiaShu() {
            return pingJiaShu;
        }

        public void setPingJiaShu(int pingJiaShu) {
            this.pingJiaShu = pingJiaShu;
        }

    }
}
