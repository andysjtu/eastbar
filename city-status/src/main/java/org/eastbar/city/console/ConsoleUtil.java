package org.eastbar.city.console;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by andyliang on 6/29/15.
 */
public class ConsoleUtil {
    private final InputStream in;


    public ConsoleUtil(InputStream in) {
        this.in = in;
    }

    public String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return  reader.readLine();
    }

    public static void main(String[] args) throws IOException {
        ConsoleUtil consoleUtil = new ConsoleUtil(System.in);
        String line;
        do{
            System.out.print("请输入命令：");
            line = consoleUtil.readLine().trim();
            System.out.println("line is "+line);
        }while(!"quit".equals(line));
    }
}
