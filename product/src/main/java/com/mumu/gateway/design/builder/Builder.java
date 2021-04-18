package com.mumu.gateway.design.builder;

public class Builder {
    public static void main(String[] args){
        CourseBuilder courseBuilder = new CourseBuilder()
                .addName("设计模式")
                .addPpt("PPT")
                .addVideo("回放视频")
                .addNote("课堂笔记")
                .addHomework("课后作业");

        System.out.print(courseBuilder.builder());
    }
}
