package opendataio;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class MyFileSystem extends LocalFileSystem {
  @Override
  public FSDataInputStream open(Path f) throws IOException {
    return new MyFSDataInputStream(super.open(f).getWrappedStream());
  }
}
