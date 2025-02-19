package opendataio;

import org.apache.hadoop.fs.FSDataInputStream;

import java.io.InputStream;

public class MyFSDataInputStream extends FSDataInputStream {
  public MyFSDataInputStream(InputStream in) {
    super(in);
  }

  public String getMetrics() {
    return "mockMetrics";
  }
}
