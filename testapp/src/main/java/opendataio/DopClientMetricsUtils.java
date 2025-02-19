package opendataio;

import org.apache.hadoop.fs.FSDataInputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DopClientMetricsUtils {
  private static final Map<Class<?>, Method> METHOD_MAP = new HashMap<>();
  public static String getMetrics(FSDataInputStream is) {
    try {
      Method method = METHOD_MAP.get(is.getClass());
      if (method == null) {
        method = is.getClass().getMethod("getMetrics");
        METHOD_MAP.put(is.getClass(), method);
      }
      String metrics = (String) method.invoke(is);
      return metrics;
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
