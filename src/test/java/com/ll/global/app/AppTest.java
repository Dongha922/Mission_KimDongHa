package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    //리팩토링
    private String run(final String cmd){
        final Scanner scanner = TestUtil.genScanner(cmd.stripIndent().trim() + "\n종료");
        final ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        new App(scanner).run();

        final String out = byteArrayOutputStream.toString().trim();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
        return out.trim();
    }
    @Test
    @DisplayName("프로그램 시작 시 \"== 명언 앱 ==\" 출력")
    void t1() {

        final String out = run("""
                
                """);
        assertThat(out)
                .contains("== 명언 앱 ==");
    }
    @Test
    @DisplayName("종료")
    void t2() {
        final String out = run("""
                
                """);

    }
    @Test
    @DisplayName("등록")
    void t3() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
               
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");

    }
    @Test
    @DisplayName("등록할 때마다 번호 1씩 증가")
    void t4() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                                
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .doesNotContain("2번 명언이 등록되었습니다.");

    }
    @Test
    @DisplayName("등록할 때마다 번호 1씩 증가, 2건 등록")
    void t5(){
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .doesNotContain("3번 명언이 등록되었습니다.");

    }
    @Test
    @DisplayName("목록")
    void t6() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }
    @Test
    @DisplayName("목록 2")
    void t7() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 홍길동 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }


}