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
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                JDBCUtils utils = new JDBCUtils();
                utils.getConnection();
                List<user> users = utils.getSelect();

                for(user theuser: users){
                    out.write(JwtTokenUtils.createToken(theuser.getId(), theuser.getWechat_id(), true));
                    out.write("\r\n");
                }
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
