package com.ll.global.app;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    public App(final Scanner scanner){
        this.scanner = scanner;
    }

    public void run(){
        System.out.println("== 명언 앱 ==");


        System.out.println("명언 : ");
        System.out.println("작가 : ");
        System.out.println("1번 명언이 등록되었습니다.");
    }
}
