package com.ll.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner scanner;
    private int lastQuotationId;
    private List<com.ll.domain.Quotation> quotations;

    //명어 넣을 배열 객체 한개 만들기
    //가변크기로 만들기
    public App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotations = new ArrayList<>();
    }

    public void run() {
        System.out.println("===명언앱 ===");
        while (true) {
            System.out.printf("명령) ");
            String cmd = scanner.nextLine();
            //request 객체 생성
            Rq rq = new Rq(cmd);
            System.out.println("rq.getAction : " + rq.getAction());
            System.out.println("rq.getParamAsInt : " + rq.getParamAsInt("id", 0));


            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }

    //등록
    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        com.ll.domain.Quotation quotation = new com.ll.domain.Quotation(id, content, authorName);
        quotations.add(quotation);
        System.out.println(id + "번 명언이 등록되었습니다.");

    }

    //목록
    private void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("---------------------");
        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다");
        for (int i = quotations.size() - 1; i >= 0; i--) {
            com.ll.domain.Quotation quotation = quotations.get(i);

            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    //삭제
    private void actionRemove(Rq rq) {
        //명령어와 id값을 찾아라.
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; //함수를 끝낸다.
        }int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotations.remove(index);

        System.out.printf("%d번 명언을 삭제되었습니다.\n", id);
    }

    private int findQuotationIndexById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            com.ll.domain.Quotation quotation = quotations.get(i);

            if (quotation.id == id) {
                return i;
            }
        }

        return -1;

    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; //함수를 끝낸다.
        }
        System.out.println(id + "번 명언을 수정합니다");

    }
}
