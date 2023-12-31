package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

    public Member detail(String memberType, String userId){


        Member member= null;

        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용



            String sql = "select m.member_type, m.user_id, m.password, m.name " +
                    "    ,md.mobile_no " +
                    "    ,md.marketing_yn " +
                    "    ,md.register_date " +
                    " from member m " +
                    " left join member_detail md on md.member_type = m.member_type and m.user_id = md.user_id " +
                    " where m.member_type = ? and m.user_id = ? ; ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberType);
            preparedStatement.setString(2, userId);


            rs = preparedStatement.executeQuery();

            if (rs.next()) {

                member = new Member();

                member.setMemberType( rs.getString("member_type"));
                member.setUserId(rs.getString("user_id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setMobileNo(rs.getString("mobile_no"));
                member.setMarketingYn(rs.getBoolean("marketing_yn"));
                member.setRegisterDate(rs.getDate("register_date"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return member;
    }

    public List<Member> list(){

        List<Member> memberList = new ArrayList<>();

        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Statement statement = null;
        ResultSet rs = null;

        String memberTypeValue = "email";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용

            String sql = "select member_type, user_id, password, name " +
                    " from member " +
                    " where member_type = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);


            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String memberType = rs.getString("member_type");
                String userId = rs.getString("user_id");
                String password = rs.getString("password");
                String name = rs.getString("name");

                Member member = new Member();
                member.setMemberType(memberTypeValue);
                member.setUserId(userId);
                member.setPassword(password);
                member.setName(name);

                memberList.add(member);
                System.out.println(memberType + ", " + userId + ", " + password + ", " + name);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return memberList;
    }
    public void dbSelect() {

        //5개
        //1. ip(domain)
        //2. port
        //3. 계정
        //4. 패스워드
        //5. 인스턴스

        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";

        //1. 드라이버 로드
        //2. 커넥션 객체 생성
        //3. 스테이트먼트 객체 생성
        //4. 쿼리 실행
        //5. 결과 수행
        //6. 객체 연결 해제(close)


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Statement statement = null;
        ResultSet rs = null;

        String memberTypeValue = "email";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용


            String sql = "select member_type, user_id, password, name " +
                    " from member " +
                    " where member_type = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);


            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String memberType = rs.getString("member_type");
                String userId = rs.getString("user_id");
                String password = rs.getString("password");
                String name = rs.getString("name");

                System.out.println(memberType + ", " + userId + ", " + password + ", " + name);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    /**
     * 회원가입함수
     * @param member
     */
    public void register(Member member) {



        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Statement statement = null;
        ResultSet rs = null;



        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용


            String sql = "insert into member(member_type, user_id, password, name) " +
                    " values (?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberType());
            preparedStatement.setString(2, member.getUserId());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.setString(4, member.getName());

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("저장 성공");
            }else {
                System.out.println("저장 실패");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void dbUpdate() {



        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Statement statement = null;
        ResultSet rs = null;

        String memberTypeValue = "email";
        String userIdValue ="zerobase@naver.com";
        String passwordValue = "9999";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용


            String sql = "update member " +
                    " set password = ? " +
                    " where member_type = ? " +
                    "  and user_id = ?; ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,passwordValue);
            preparedStatement.setString(2,memberTypeValue);
            preparedStatement.setString(3,userIdValue);

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("수정 성공");
            }else {
                System.out.println("저장 실패");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    /**
     * 회원 탈퇴
     */
    public void withdraw(Member member) {



        String url = "jdbc:mariadb://localhost:33062/testdb1";
        String dbUserId = "root";
        String dbUserPassword = "s5554549";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Statement statement = null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);

            //statement = connection.createStatement();
            //PreparedStatement preparedStatement = null;  주로 사용


            String sql = "delete from member " +
                    " where member_type = ? and user_id = ?; ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,member.getMemberType());
            preparedStatement.setString(2,member.getUserId());

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("삭제 성공");
            }else {
                System.out.println("삭제 실패");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }



}
