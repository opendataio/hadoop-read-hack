package opendataio;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: <input path>");
            return;
        }
        Path path = new Path(args[0]);
        FileSystem fs = path.getFileSystem(new Configuration());
        byte[] buffer = new byte[10];
        buffer[0] = 1;
        try (FSDataInputStream is = fs.open(path)) {
            System.out.println("Calling FSDataInputStream.read");
            is.read(0, buffer, 0, 10);
            System.out.println(new String(buffer));
        }
        System.out.println("Hello World!");
    }
}