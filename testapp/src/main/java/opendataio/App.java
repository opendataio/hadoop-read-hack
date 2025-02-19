package opendataio;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class App {
    public static void main(String[] args)
        throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: <input path>");
            return;
        }
        Path path = new Path(args[0]);
        Configuration conf = new Configuration();
        conf.set("fs.file.impl", MyFileSystem.class.getName());
        conf.set("contextId", "context111");
        FileSystem fs = path.getFileSystem(conf);
        byte[] buffer = new byte[10];
        buffer[0] = 1;
        try (FSDataInputStream is = fs.open(path)) {
            System.out.println("Calling FSDataInputStream.read");
            is.read(0, buffer, 0, 10);
            System.out.println(new String(buffer));
            String metrics = DopClientMetricsUtils.getMetrics(is);
            System.out.println(metrics);
        }
        System.out.println("Hello World!");
    }
}