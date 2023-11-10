package db;


import java.util.Scanner;

public class DbTestMain {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.dbSelect();

    }
    public static void main1(String[] args) {
        MemberService memberService = new MemberService();

        Scanner sc = new Scanner(System.in);
        String memberType = "email";

        System.out.println("탈퇴할 회원 아이디를 입력해주세요.");

       System.out.println("아이디 입력:>>>> ");
       String userId = sc.next();
//
//        System.out.println("비번 입력:>>>> ");
//        String password = sc.next();
//
//        System.out.println("이름 입력:>>>> ");
//        String name = sc.next();
//
        Member member = new Member();
        member.setMemberType(memberType);
        member.setUserId(userId);
//        member.setPassword(password);
//        member.setName(name);
//
        memberService.withdraw(member);
    }
}
