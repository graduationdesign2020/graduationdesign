package org.example;

import java.util.List;
import java.io.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        try {

            File writeName = new File("/Users/mac/Desktop/token.txt");
            File teacherName = new File("/Users/mac/Desktop/teachertoken.txt");
            File studentName = new File("/Users/mac/Desktop/studenttoken.txt");
            try (
                    FileWriter writer = new FileWriter(writeName);
                    BufferedWriter out = new BufferedWriter(writer);
                    FileWriter teacherwriter = new FileWriter(teacherName);
                    BufferedWriter teacherout = new BufferedWriter(teacherwriter);
                    FileWriter studentwriter = new FileWriter(studentName);
                    BufferedWriter studentout = new BufferedWriter(studentwriter);
            ) {
                JDBCUtils utils = new JDBCUtils();
                utils.getConnection();
                List<user> users = utils.getSelect();

                for(user theuser: users){
                    String jwt = JwtTokenUtils.createToken(theuser.getId(), theuser.getWechat_id(), true);
                    out.write(jwt);
                    out.write("\r\n");
                    if(theuser.getAuth().equals("ROLE_STUDENT")){
                        studentout.write(jwt);
                        studentout.write("\r\n");
                    }
                    else {
                        teacherout.write(jwt);
                        teacherout.write("/r/n");
                    }
                }
                studentout.flush();
                teacherout.flush();
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
